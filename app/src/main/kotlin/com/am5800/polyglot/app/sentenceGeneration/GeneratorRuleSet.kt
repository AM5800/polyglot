package com.am5800.polyglot.app.sentenceGeneration

enum class Head {
  IsLeft,
  IsRight
}

interface RuleNode {
  fun <TUp, TDown> visit(visitor: GeneratorRuleSetVisitor<TUp, TDown>, data: TDown): TUp
}

class LeafRuleNode(val tag: GeneratorTag) : RuleNode {
  override fun <TUp, TDown> visit(visitor: GeneratorRuleSetVisitor<TUp, TDown>, data: TDown): TUp {
    return visitor.visitLeaf(this, data)
  }
}

class BinaryNode(val left: RuleNode, val right: RuleNode, val format: String, val head: Head) : RuleNode {
  override fun <TUp, TDown> visit(visitor: GeneratorRuleSetVisitor<TUp, TDown>, data: TDown): TUp {
    return visitor.visitBinaryNode(this, data)
  }
}

class GeneratorRuleSet(private val rootNode: RuleNode) {
  class RuleSetDefinition {
    private class DefinitionNode(val left: GeneratorTag, val right: GeneratorTag, val format: String, val head: Head)

    private class Link(val name: String) : GeneratorTag

    private val nodes = mutableMapOf<String, DefinitionNode>()

    fun rootRule(format: String, left: GeneratorTag, right: GeneratorTag, head: Head) {
      nodes.put("S", DefinitionNode(left, right, format, head))
    }

    fun rootRule(format: String, left: GeneratorTag, right: String, head: Head) {
      nodes.put("S", DefinitionNode(left, Link(right), format, head))
    }

    fun rule(nonTerminalName: String, format: String, left: GeneratorTag, right: GeneratorTag, head: Head) {
      nodes.put(nonTerminalName, DefinitionNode(left, right, format, head))
    }

    private fun buildRecursive(nodeName: String): RuleNode {
      val node = nodes[nodeName]!!
      nodes.remove(nodeName)

      val head = node.left
      val dependent = node.right

      val headNode = if (head is Link) buildRecursive(head.name) else LeafRuleNode(head)
      val dependentNode = if (dependent is Link) buildRecursive(dependent.name) else LeafRuleNode(dependent)

      return BinaryNode(headNode, dependentNode, node.format, node.head)
    }

    fun build(): RuleNode {
      val result = buildRecursive("S")
      if (!nodes.isEmpty()) throw Exception("Unreachable non terminal(s) found: " + nodes.map { it.key }.joinToString(", "))
      return result
    }
  }

  companion object {
    fun create(init: RuleSetDefinition.() -> Unit): GeneratorRuleSet {
      val definition = RuleSetDefinition()
      init(definition)
      return GeneratorRuleSet(definition.build())
    }
  }

  fun <TUp, TDown> visit(visitor: GeneratorRuleSetVisitor<TUp, TDown>, data: TDown): TUp {
    return rootNode.visit(visitor, data)
  }
}