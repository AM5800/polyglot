package com.am5800.polyglot.app.sentenceGeneration

interface GeneratorRuleSetVisitor<T> {
  fun visitLeaf(leafNode: GeneratorRuleSet.LeafRuleNode): T
  fun visitBinaryNode(binaryNode: GeneratorRuleSet.BinaryNode): T
}