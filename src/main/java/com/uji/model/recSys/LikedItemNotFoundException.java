package com.uji.model.recSys;

public class LikedItemNotFoundException extends NullPointerException {

  private  String itemName;

  public LikedItemNotFoundException(String itemName) {
    super("El item '" + itemName + "' no está en los datos.");
    this.itemName = itemName;
  }

  public String getItemName(){
    return itemName;
  }


}