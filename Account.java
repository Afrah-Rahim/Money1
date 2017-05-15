
/**
 * Write a description of class Account here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Account
{
    // instance variables - replace the example below with your own
    private String name;
    private String id;
    private Money balance = new Money(0, 0);

    /**
     * Constructor for objects of class Account that sets the name, ID, and balance of the account.
     */
    public Account(String nameInput, String idInput, Money balanceInput)
    {
        this.name = nameInput;
        this.id = idInput;
        this.balance = balanceInput;
    }
    
    public Account(Account other)
    {
        this.name = other.name;
        this.id = other.id;
        this.balance = other.balance;
    }

    /**
     * deposit will add the current money in this.balance with the money from the parameter using the add method in Money.
     * The Money object returned from the add method will replace this.balance. If Money other is less than $0.01, then
     * this.balance won't be changed.
     * 
     * @param   Money other   The money that will be added to this.balance
     */
    public void deposit(Money other)
    {
        if(other.compareTo(new Money (0, 0)) == 0 || other.compareTo(new Money (0, 0)) == 1)
        {
            this.balance = this.balance.add(other);
        }
    }
    
    /**
     * withdraw will subtract the current money in this.balance with the money from the parameter using the subtract method in Money.
     * The Money object returned from the subtract method will replace this.balance. If Money other is greater than this.balance,
     * then this.balance won't be changed.
     * 
     * @param   Money other   The money that will be subtracted from this.balance
     */
    public void withdraw(Money other)
    {
        if(other.compareTo(this.balance) == 0 || other.compareTo(this.balance) == -1)
        {
            this.balance = this.balance.subtract(other);
        }
    }
    
    /**
     * transfer will transfer money from Account other into this Account
     * 
     * @param   Account other   The account that cash will be taken out of.
     *          Money cash      The amount of money that will be taken out of Account other.
     */
    public void transfer(Account other, Money cash)
    {
        this.balance = this.balance.add(cash);
        other.balance = other.balance.subtract(cash);
    }
    
    /**
     * toString will return the name, id, and balance from this Account as a String
     * Ex. "Name, 001, $10.00"
     * 
     * @return  The string containing the name, id, and balance from this Account
     */
    public String toString()
    {
        return "" + this.name + ", " + this.id + ", " + this.balance.toString();
    }
    
    /**
     * returnID will return id from this Account as a String.
     * This is used by Bank in it's search method.
     * 
     * @return  id from this account.
     */
    public String returnID()
    {
        return new String(id);
    }
    
    /**
     * equals compares 2 Accounts and checks if their instance variables are the same.
     * 
     * @param  Account other    The Account that is being compared to this account
     * @return true if this account is the same as the other account
     */
    public boolean equals(Account other)
    {
        if(this.name.equals(other.name) && this.id.equals(other.id) && this.balance.equals(other.balance))
        {
            return true;
        }
        
        return false;
    }
}
