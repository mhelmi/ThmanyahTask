package com.github.mhelmi.thmanyahtask.ui.model

enum class SectionType(val type: String) {
  SQUARE("square"),
  TWO_LINES_GRID("2_lines_grid"),
  BIG_SQUARE("big_square"),
  QUEUE("queue");

  companion object {
    fun fromType(type: String): SectionType? {
      return entries.firstOrNull { it.type == type }
    }
  }
}