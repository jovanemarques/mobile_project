package ca.centennialcollege.comp304_miniproject.models;

public enum OrderStatus {
    RECEIVED,
    ASSIGNED_TO_DELIVERER,
    IN_TRANSIT,
    DELIVERED,
    NOT_DELIVERED;

    public String getDescription() {
        switch (this) {
            case RECEIVED:
                return "Received";

            case ASSIGNED_TO_DELIVERER:
                return "Assigned to Deliverer";

            case IN_TRANSIT:
                return "In transit";

            case DELIVERED:
                return "Delivered";

            case NOT_DELIVERED:
                return "Not Delivered";
        }

        return "";
    }
}
