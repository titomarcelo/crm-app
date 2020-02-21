package com.tmao.crm.product.domain;

public enum Category {

    NON_ALCOHOLIC_DRINK {
        @Override
        public String getDescription() {
            return "Bebida não alcoólica";
        }
    },

    ALCOHOLIC_DRINK {
        @Override
        public String getDescription() {
            return "Bebida alcoólica";
        }
    },

    SNACK {
        @Override
        public String getDescription() {
            return "Petisco";
        }
    },

    DISH {
        @Override
        public String getDescription() {
            return "Prato";
        }
    },

    DESSERT {
        @Override
        public String getDescription() {
            return "Sobremesa";
        }
    };

    public abstract String getDescription();

    public String getValue() {
        return name();
    }

}
