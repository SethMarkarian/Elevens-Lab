import java.util.List;
import java.util.ArrayList;
public class ThirteensBoard extends Board
{
   private static final int BOARD_SIZE = 10;
   private static final int[] POINT_VALUES =
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0};
   
   public ThirteensBoard() {
       super(BOARD_SIZE, POINT_VALUES);
   }
   public boolean isLegal(List<Integer> selectedCards) {
       return (containsKing(selectedCards) && selectedCards.size() == 1) || (containsPairSum13(selectedCards) && selectedCards.size() == 2);
   }
   public boolean anotherPlayIsPossible() {
       List<Integer> position = cardIndexes();
       return containsKing(position) || containsPairSum13(position);
   }
   private boolean containsKing(List<Integer> selectedCards) {
       for(int x : selectedCards) {
           if(cardAt(x).rank().equals("king")) {
               return true;
           }
       }
       return false;
   }
   private boolean containsPairSum13(List<Integer> selectedCards) {
       for(int x : selectedCards) {
            for(int y : selectedCards) {
                if(cardAt(x).pointValue() + cardAt(y).pointValue() == 13) {
                    return true;
                }
            }
        }
        return false;
   }
}
