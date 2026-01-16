package Generics_Pratice_Questions.E_Commerce_Recommendation;
import java.util.*;
import java.util.stream.Collectors;

public class Recommender<T extends Product>
{

 // Recommend products based on user interest and sort them

   public List<T> recommend(List<T> products , User user , Comparator<? super T> comparator)
   {
       // Filter based on Category matching user interest

       List<T> filtered = new ArrayList<>(products.stream().filter(p -> user.getInterests().contains(p.getCategory()))
               .collect(Collectors.toList()));

       // .collect(Collectors.toList());

       // Sort using the provided comparator

       filtered.sort(comparator);

       return filtered;

   }




}
