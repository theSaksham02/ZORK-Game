package org.uob.a1;


public class Inventory{
    private String[] items;
    private final int MAX_ITEM = 10;
    private int curItem;
    
    public Inventory(){
        items = new String[MAX_ITEM];
        curItem= 0;
    }
    public void addItem(String item){
        if(curItem< MAX_ITEM){
            items[curItem++] = item;
            ; 
        } else{
            System.out.println("Sorry, Inventory is full of Items ;" + item);
        }
    }

    public int hasItem(String item){
        for (int i = 0; i<curItem; i++){
            if(items[i].equals(item)){
                return i;
            }
        }
        return -1;
    }
    
    public void removeItem(String item) {
        int pos = hasItem(item);
        if(pos >= 0) {
            for(int i = pos; i < curItem - 1; i++) {
                items[i] = items[i + 1];
            }
            curItem--;
        }
    }

    public String displayInventory() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < curItem; i++) {
            sb.append(items[i]).append(" "); 
        }
        return sb.toString(); 
    }

}
    

