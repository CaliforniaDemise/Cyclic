package com.lothrazar.cyclicmagic.block.buildershape;
public enum StructureBuilderType {

  FACING, SQUARE, CIRCLE, SOLID, SPHERE, DIAGONAL, DOME, CUP, PYRAMID, TUNNEL;

  public static final StructureBuilderType[] SORTED = new StructureBuilderType[] {
      FACING, SQUARE, SOLID, DIAGONAL, PYRAMID, CIRCLE, TUNNEL, SPHERE, DOME, CUP
  };

  public static StructureBuilderType getNextType(StructureBuilderType btype) {
    int type = btype.ordinal();
    type++;
    if (type > SPHERE.ordinal()) {
      type = FACING.ordinal();
    }
    return StructureBuilderType.values()[type];
  }

  public boolean hasHeight() {
    if (this == SPHERE || this == DIAGONAL || this == DOME || this == CUP)
      return false;
    return true;
  }

  public String shortcode() {
    switch (this) {
      case CIRCLE:
        return "CH";
      case DIAGONAL:
        return "DI";
      case FACING:
        return "WA";
      case SOLID:
        return "SO";
      case SPHERE:
        return "SP";
      case SQUARE:
        return "SQ";
      case DOME:
        return "DO";
      case CUP:
        return "CU";
      case PYRAMID:
        return "PY";
      case TUNNEL:
        return "TU";
    }
    return "";
  }
}