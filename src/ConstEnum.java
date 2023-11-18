public enum ConstEnum {
        Curso("curso"),
        Fase("fase"),
        Disciplina("disciplina"),
        Professor("professor"),
        Trailer ("trailer");
        
        ConstEnum(String v) {
          value = v;
        }
        private String value;
        public String getValue() {
          return value;
        }
      }
