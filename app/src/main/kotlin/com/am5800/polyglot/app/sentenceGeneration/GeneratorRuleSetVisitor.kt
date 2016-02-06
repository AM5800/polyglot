package com.am5800.polyglot.app.sentenceGeneration

interface GeneratorRuleSetVisitor<TUp, TDown> {
  fun visitLeaf(leafNode: LeafRuleNode, data: TDown): TUp
  fun visitBinaryNode(binaryNode: BinaryNode, data: TDown): TUp
}