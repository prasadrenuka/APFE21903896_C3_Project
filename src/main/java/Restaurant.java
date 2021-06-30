import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant
{
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime)
    {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen()
    {
     LocalTime time=LocalTime.now();
     int value=time.compareTo(openingTime);
     int value1=time.compareTo(closingTime);
     if(value>0 && value1<0)
         return true;
     return false;
    }

    public LocalTime getCurrentTime()
    {
        return  LocalTime.now();
    }

    public List<Item> getMenu()
    {
        return menu;

    }

    private Item findItemByName(String itemName)
    {
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price)
    {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException
    {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails()
    {
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName()
    {
        return name;
    }
    public int price_of_selected_iteams(List<String> selected_iteams)
    {
        int total_price=0;
        for(int i=0;i<selected_iteams.size();i++)
        {
            for(int j=0;j<menu.size();j++)
            {
                int nub = selected_iteams.get(i).compareTo(menu.get(j).getName());
                if (nub == 0)
                {
                    total_price += menu.get(j).getPrice();
                }
            }
        }
        return total_price;
    }

}
