package com.codebase.core.protocol.redis;

public enum ResponseType {
        SIMPLE_STRING('+'),//
        ERROR('-'),//
        INTEGER(':'),//
        BULK_STRING('$'),//
        ARRAY('*'),//
        ;

        public final byte desc;
        private ResponseType(char desc) {
            this.desc = (byte) desc;
        }
}
