package com.tom.atm;

public class Transaction {

   String account;
   String date;
   int amount;
   int type;

   public Transaction(String account, String date, int amount, int type) {
      this.account = account;
      this.date = date;
      this.amount = amount;
      this.type = type;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }

   public void setType(int type) {
      this.type = type;
   }

   public String getAccount() {
      return account;
   }

   public String getDate() {
      return date;
   }

   public int getAmount() {
      return amount;
   }

   public int getType() {
      return type;
   }




}
