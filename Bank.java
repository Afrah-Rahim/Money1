
/**
 * Write a description of class Bank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bank
{
    private String nameOfBank;
    private Account[] accounts;
    private int numOfAccounts;
    private int maxAccounts = 50;
    private int searchIndex = 0;
    
    // Deposit and Withdraw can be void
    
    
    /**
     * Constructor for objects of class Bank
     */
    public Bank(String name)
    {
        this.nameOfBank = name;
        this.accounts = new Account[maxAccounts];
        for(int i = 0; i < maxAccounts; i++)
        {
            accounts[i] = new Account("None", "None", new Money (0, 0));
        }
        this.numOfAccounts = 0;
    }

    /**
     * addAccount will add accountInput to accounts in the next open space.
     * Precondition - accountInput must be a vaild Account with a name, ID, and Money value.
     * Postcondition - accountInput will be added to the account array in the next open space.
     */
    public void addAccount(Account accountInput)
    {
        if(numOfAccounts >= maxAccounts)
        {
            System.out.println("Error: Too many accounts!"); 
        }
        else
        {
            accounts[numOfAccounts] = new Account(accountInput);
            this.numOfAccounts++;
        }
    }
    
    /**
     * search will return an account with the inputted id if it exists in the accounts array.
     * 
     * @param - String id - the id of the account that will be searched for in the accounts array.
     * @return - Account - An account object from the accounts array that has the same id 
     *                     as the String in the parameter. If an account isn't found in the accounts
     *                     array with the same id as the String in the parameter, an error account
     *                     will be returned instead.
     *                     
     *                     new Account("Error", "Error", new Money (0, 0));
     */
    public Account search(String id)
    {
        searchIndex = 0;
        boolean loop = true;
        
        while(loop)
        {
            if (accounts[searchIndex].returnID().equals(id))
            {
                return new Account(accounts[searchIndex]);
            }
            searchIndex++;
            
            if (searchIndex == numOfAccounts)
            {
                loop = false;
            }
        }
        
        return new Account("Error", "Error", new Money (0, 0));
    }
    
    /**
     * deposit will add the specified Money in the parameter to the Money in the account located
     *         in accounts that matches the id specified in the parameter.
     *         
     * @param - String id - The id of the account in accounts that Money add should be added to.
     *                      If an account in accounts doesn't have the same id, Money add will be
     *                      added to an error account, which won't exist after this method is ran.
     *          Money add - The amount of money that should be added to the account in accounts that
     *                      matches the String id in the parameter. If Money add is negative,
     *                      nothing will be added to the account.
     */
    public void deposit(String id, Money add)
    {
        Account tempAccount = search(id);
        tempAccount.deposit(add);
        accounts[searchIndex] = tempAccount;
    }
    
    /**
     * deposit will subtract the specified Money in the parameter from the Money in the account 
     *         located in accounts that matches the id specified in the parameter.
     *         
     * @param - String id - The id of the account in accounts that Money sub should be subtracted from.
     *                      If an account in accounts doesn't have the same id, Money sub will be
     *                      subtracted from an error account, which won't exist after this method is ran.
     *          Money sub - The amount of money that should be subtracted from the account in 
     *                      accounts that matches the String id in the parameter. If Money sub is 
     *                      greater than the money in the account, nothing will taken out.
     */
    public void withdraw(String id, Money sub)
    {
        Account tempAccount = search(id);
        tempAccount.withdraw(sub);
        accounts[searchIndex] = tempAccount;
    }
    
    /**
     * toString will return a String value with nameOfBank, numOfAccounts, and the details of each
     *          account in this Bank on separate lines.
     * Ex. "BankName, 1
     *      1. AccountName, 001, $10.00"
     * @return  The String value with nameOfBank, numOfAccounts, and the details of each
     *          account in this Bank on separate lines.
     */
    public String toString()
    {
        String full = "";
        
        full = nameOfBank + ", " + numOfAccounts;
        
        for (int index = 0; index < numOfAccounts; index++)
        {
            full += "" + "\n" + (index + 1) + ". " + this.accounts[index].toString();
        }
        
        return full;
    }
}
