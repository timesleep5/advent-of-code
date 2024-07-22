package day_10;

enum CONNECTION_OUT {
    TO_NORTH,
    TO_SOUTH,
    TO_WEST,
    TO_EAST;

    CONNECTION_IN getCorrespondingConnectionIn() {
        return switch (this) {
            case TO_NORTH -> CONNECTION_IN.FROM_SOUTH;
            case TO_SOUTH -> CONNECTION_IN.FROM_NORTH;
            case TO_WEST -> CONNECTION_IN.FROM_EAST;
            case TO_EAST -> CONNECTION_IN.FROM_WEST;
        };
    }
}
