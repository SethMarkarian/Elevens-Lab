import java.util.List;
import java.util.ArrayList;
public class TensBoard extends Board
{
   private static final int BOARD_SIZE = 13;
   private static final int[] POINT_VALUES =
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0};
   public TensBoard() {
        super(BOARD_SIZE, POINT_VALUES);
   }
   public boolean isLegal(List<Integer> selectedCards) {
       return (containsPairSum10(selectedCards) && selectedCards.size() == 2) || 
       ((containsRank(selectedCards) || containsSuit(selectedCards)) && selectedCards.size() == 4);
   }
   public boolean anotherPlayIsPossible() {
       List<Integer> position = cardIndexes();
       return containsPairSum10(position) || (containsRank(position) || containsSuit(position));
   }
   private boolean containsPairSum10(List<Integer> selectedCards) {
       for(int j = 0; j < selectedCards.size(); j++) {
            for(int i = 0; i < selectedCards.size(); i++) {
                if(i != j) {
                    if(cardAt(selectedCards.get(j)).pointValue() + cardAt(selectedCards.get(i)).pointValue() == 10) {
                    return true;
                    }
                }
            }
        }
        return false;
   }
   private boolean containsRank(List<Integer> selectedCards) {
       for(String x : RANKS) {
           int count = 0;
           for(int y : selectedCards) {
               if(cardAt(y).rank().equals(x)){
                   count++;
                }
            }
           if(count == 4) {
               return true;
            }
            }
       return false;
        }
   private boolean containsSuit(List<Integer> selectedCards) {
       List<String> ranks = new ArrayList<String>();
       ranks.add("10");
       ranks.add("jack");
       ranks.add("queen");
       ranks.add("king");
       List<Integer> spades = new ArrayList<Integer>();
       List<Integer> clubs = new ArrayList<Integer>();
       List<Integer> diamonds = new ArrayList<Integer>();
       List<Integer> hearts = new ArrayList<Integer>();
       int pos = 0;
       for(int x : selectedCards) {
           if(cardAt(x).suit().equals("spades")) {
               spades.add(pos);
            }
           if(cardAt(x).suit().equals("clubs")) {
               clubs.add(pos);
            }
           if(cardAt(x).suit().equals("diamonds")) {
               diamonds.add(pos);
            }
           if(cardAt(x).suit().equals("hearts")) {
               hearts.add(pos);
            }
           pos++;
       }
       
       if(spades.size() == 4) {
           for(int x : spades) {
               for(int i = 0; i < ranks.size(); i++) {
                   if(cardAt(selectedCards.get(x)).rank().equals(ranks.get(i))) {
                       ranks.remove(i);
                    }
                }
            }
        }
       if(clubs.size() == 4) {
           for(int x : clubs) {
               for(int i = 0; i < ranks.size(); i++) {
                   if(cardAt(selectedCards.get(x)).rank().equals(ranks.get(i))) {
                       ranks.remove(i);
                    }
                }
            }
        }
       if(diamonds.size() == 4) {
           for(int x : diamonds) {
               for(int i = 0; i < ranks.size(); i++) {
                   if(cardAt(selectedCards.get(x)).rank().equals(ranks.get(i))) {
                       ranks.remove(i);
                    }
                }
            }
        }
       if(hearts.size() == 4) {
           for(int x : hearts) {
               for(int i = 0; i < ranks.size(); i++) {
                   if(cardAt(selectedCards.get(x)).rank().equals(ranks.get(i))) {
                       ranks.remove(i);
                    }
                }
            }
        }
       if(ranks.size() == 0) {
           return true;
        }
       return false;
   }
}