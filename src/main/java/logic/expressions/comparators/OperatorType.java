package logic.expressions.comparators;

public enum OperatorType {
    More {
        public String represent() {
            return ">";
        }
    },
    MoreEqual {
        public String represent() {
            return ">=";
        }
    },
    Less {
        public String represent() {
            return "<";
        }
    }, LessEqual {
        public String represent() {
            return "<=";
        }
    }, Equal {
        public String represent() {
            return "=";
        }
    }, NotEqual {
        public String represent() {
            return "!=";
        }
    }
}
