

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BankTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BankTest
{
    private Bank _bank;
    
    /**
     * Default constructor for test class BankTest
     */
    public BankTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        _bank = new Bank("Test");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        _bank = null;
    }
    
    // This test will add account1 to _bank using addAccount.
    @Test
    public void testAddAccount()
    {
        Account account1 = new Account("Name", "001", new Money (10, 0));
        Account account2 = new Account("Name", "001", new Money (10, 0));
        
        _bank.addAccount(account1);
        
        assertTrue("Error in testAddAcount", account2.equals(_bank.search("001")));
    }
    
    // This test will find an account in _bank using the search method.
    @Test
    public void testSearch()
    {
        Account account1 = new Account("Name", "001", new Money (10, 0));
        Account account2 = new Account("Name2", "005", new Money (20, 0));
        
        _bank.addAccount(account1);
        _bank.addAccount(account2);
        
        // account3 is identical to account1 and account4 is identical to account2
        // This will test if the account found with search() equals the account that's identical to it
        
        Account account3 = new Account("Name", "001", new Money (10, 0));
        Account account4 = new Account("Name2", "005", new Money (20, 0));
        
        assertTrue("Error in testSearch", account3.equals(_bank.search("001")));
        assertTrue("Error in testSearch", account4.equals(_bank.search("005")));
        // This test will check if the search method returns the correct error account if
        // it can't find an account with the same id.
        
        Account account5 = new Account("Error", "Error", new Money (0, 0));
        
        assertTrue("Error in testSearch", account5.equals(_bank.search("010")));
    }
    
    // This will test the deposit method in Bank
    @Test
    public void testDepost()
    {
        Account account1 = new Account("Name", "001", new Money (10, 0));
        Account account2 = new Account("Name2", "005", new Money (20, 0));
        
        _bank.addAccount(account1);
        _bank.addAccount(account2);
        
        // $30 will be added to _bank.accounts[0]
        // 20 cents will be added to _bank.accounts[1]
        
        _bank.deposit("001", new Money(30, 0));
        _bank.deposit("005", new Money(0, 20));
        
        // account3 should be equal to _bank.accounts[0]
        // and account4 should be equal to _bank.accounts[1]
        // This test will use the search method to see if this is correct.
        
        Account account3 = new Account("Name", "001", new Money (40, 0));
        Account account4 = new Account("Name2", "005", new Money (20, 20));
        
        assertTrue("Error in testDeposit", account3.equals(_bank.search("001")));
        assertTrue("Error in testDeposit", account4.equals(_bank.search("005")));
    }
    
    // This will test the withdraw method in Bank
    @Test
    public void testWithdraw()
    {
        Account account1 = new Account("Name", "001", new Money (10, 0));
        Account account2 = new Account("Name2", "005", new Money (20, 0));
        
        _bank.addAccount(account1);
        _bank.addAccount(account2);
        
        // $5 will be withdrawn from _bank.accounts[0]
        // 50 cents will be withdrawn from _bank.accounts[1]
        
        _bank.withdraw("001", new Money(5, 0));
        _bank.withdraw("005", new Money(0, 50));
        
        // account3 should be equal to _bank.accounts[0]
        // and account4 should be equal to _bank.accounts[1]
        // This test will use the search method to see if this is correct.
        
        Account account3 = new Account("Name", "001", new Money (5, 0));
        Account account4 = new Account("Name2", "005", new Money (19, 50));
        
        assertTrue("Error in testWithdraw", account3.equals(_bank.search("001")));
        assertTrue("Error in testWithdraw", account4.equals(_bank.search("005")));
    }
    
    // This will test the toString method in Bank
    @Test
    public void testToString()
    {
        Account account1 = new Account("Name", "001", new Money (10, 0));
        
        _bank.addAccount(account1);
        
        String bank2String = "Test, 1" + "\n" + "1. Name, 001, $10.00";
        // account1 is equal to account2 and nameOfBank is the same for both banks, so both banks
        // should return the same String.
        // This test will test if this is true.
        
        assertTrue("Error in testToString", _bank.toString().equals(bank2String));
    }
    
    @Test
    public void testPrivacyLeak()
    {
        //Test search
        Account account1 = new Account("Name", "001", new Money (10, 0));
        _bank.addAccount(account1);
        
        Account account2 = _bank.search("001");
        
        account2.deposit(new Money(10, 0));
        
        assertTrue("Error in testPrivacyLeak", _bank.search("001").toString().equals("Name, 001, $10.00"));
        
        //Test addAccount
        
        account1.deposit(new Money(10, 0));
        
        assertTrue("Error in testPrivacyLeak", _bank.search("001").toString().equals("Name, 001, $10.00"));
    }
}
