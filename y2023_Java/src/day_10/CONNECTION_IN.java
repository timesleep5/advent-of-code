package day_10;

enum CONNECTION_IN {
    FROM_NORTH,
    FROM_SOUTH,
    FROM_WEST,
    FROM_EAST;

    CONNECTION_OUT getCorrespondingConnectionOut(char symbol) {
        return switch (symbol) {
            case '|' -> this.equals(FROM_NORTH) ? CONNECTION_OUT.TO_SOUTH : CONNECTION_OUT.TO_NORTH;
            case '-' -> this.equals(FROM_WEST) ? CONNECTION_OUT.TO_EAST : CONNECTION_OUT.TO_WEST;
            case 'L' -> this.equals(FROM_NORTH) ? CONNECTION_OUT.TO_EAST : CONNECTION_OUT.TO_NORTH;
            case 'J' -> this.equals(FROM_NORTH) ? CONNECTION_OUT.TO_WEST : CONNECTION_OUT.TO_NORTH;
            case '7' -> this.equals(FROM_SOUTH) ? CONNECTION_OUT.TO_WEST : CONNECTION_OUT.TO_SOUTH;
            case 'F' -> this.equals(FROM_SOUTH) ? CONNECTION_OUT.TO_EAST : CONNECTION_OUT.TO_SOUTH;
            default -> throw new IllegalArgumentException("unknown connection type: " + symbol);
        };
    }
}
