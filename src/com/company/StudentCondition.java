package com.company;

public enum StudentCondition {
    ODRABIAJACY
            {
                @Override public String toString() {
                    return "ODRABIAJACY";
                }
            },
    CHORY {
        @Override public String toString() {
            return "CHORY";
        }
    },
    NIEOBECNY {
        @Override public String toString() {
            return "NIEOBECNY";
        }
    },
    OBECNY {
        @Override
        public String toString() {
            return "OBECNY";
        }
    }

}
