package kisonar.poc.learning.java.myinterview.berlinclock.logic.converter;

import java.time.LocalTime;

public class Display {

      private Converter converter;
      private int something;

      public Display(int something) {
            this.something = something;
      }

      public Display(Converter converter) {
            this.converter = converter;
      }

      public Display(Converter converter, int something) {
            this.converter = converter;
            this.something = something;
      }


      public int fetchResult(LocalTime LocalTime) {
            ConvertionResult convertionResult = converter.convertToBerlinClockFormat(LocalTime);
            return 0;
      }

      public int fetchSum(int arg1, int arg2) {
            return arg1 + arg2;
      }
}