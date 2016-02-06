package com.am5800.polyglot.app.sentenceGeneration

class GeneratorRuleSet(private val rootNode: RuleNode) {
  interface RuleNode {
    fun <T> visit(visitor: GeneratorRuleSetVisitor<T>): T
  }

  class LeafRuleNode(val tag: GeneratorTag) : RuleNode {
    override fun <T> visit(visitor: GeneratorRuleSetVisitor<T>): T {
      return visitor.visitLeaf(this)
    }
  }

  class BinaryNode(val left: RuleNode, val right: RuleNode, val format: String) : RuleNode {
    override fun <T> visit(visitor: GeneratorRuleSetVisitor<T>): T {
      return visitor.visitBinaryNode(this)
    }
  }

  class RuleSetDefinition {
    private class DefinitionNode(val left: GeneratorTag, val right: GeneratorTag, val format: String)

    private class Link(val name: String) : GeneratorTag

    private val nodes = mutableMapOf<String, DefinitionNode>()

    fun rootRule(format: String, left: GeneratorTag, right: GeneratorTag) {
      nodes.put("S", DefinitionNode(left, right, format))
    }

    fun rootRule(format: String, left: GeneratorTag, right: String) {
      nodes.put("S", DefinitionNode(left, Link(right), format))
    }

    fun rule(nonTerminalName: String, format: String, left: GeneratorTag, right: GeneratorTag) {
      nodes.put(nonTerminalName, DefinitionNode(left, right, format))
    }

    private fun buildRecursive(nodeName: String): RuleNode {
      val node = nodes[nodeName]!!
      nodes.remove(nodeName)

      val head = node.left
      val dependent = node.right

      val headNode = if (head is Link) buildRecursive(head.name) else LeafRuleNode(head)
      val dependentNode = if (dependent is Link) buildRecursive(dependent.name) else LeafRuleNode(dependent)

      return BinaryNode(headNode, dependentNode, node.format)
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

  fun <T> visit(visitor: GeneratorRuleSetVisitor<T>): T {
    return rootNode.visit(visitor)
  }
}