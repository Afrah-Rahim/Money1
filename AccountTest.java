

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AccountTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AccountTest
{
    private Account _account;
    
    /**
     * Default constructor for test class AccountTest
     */
    public AccountTest()
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
        _account = new Account("Name", "001", new Money (0, 0));
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        _account = null;
    }
    
    /**
     * Test Methods
     */
    
    // This will test if money was correctly deposited into the account
    @Test
    public void testDeposit()
    {
        Money depositCash = new Money (5, 0);
        
        // Expected result is true
        
        Account expectedResult = new Account("Name", "001", depositCash);
        _account.deposit(depositCash);
        
        assertTrue ("Error in testDeposit", _account.equals(expectedResult));
    }
    
    // This will test if money was correctly withdrawn from the account
    @Test
    public void testWithdraw()
    {
        Money withdrawCash = new Money (5, 0);
        Account account2 = new Account("Name", "001", new Money (10, 0));
        
        // Expected result is true
        
        Account expectedResult = new Account("Name", "001", new Money (5, 0));
        account2.withdraw(withdrawCash);
        
        assertTrue ("Error in testWithdraw", account2.equals(expectedResult));
    }
    
    // This will test if money was correctly transfered from one account to another
    @Test
    public void testTransfer()
    {
        Money transferCash = new Money (5, 0);
        Account account2 = new Account("Name", "001", new Money (12, 0));
        
        _account.transfer(account2, transferCash);
        
        // _account should have $5 and account2 shoudl have $7
        
        Account expectedResult1 = new Account("Name", "001", new Money (5, 0));
        Account expectedResult2 = new Account("Name", "001", new Money (7, 0));
        
        assertTrue ("Error in testTransfer", _account.equals(expectedResult1));
        assertTrue ("Error in testTransfer", account2.equals(expectedResult2));
    }
    
    // This will test if toString correctly outputs all the information from Account
    @Test
    public void testToString()
    {
        String expectedResult = "Name, 001, $0.00";
        String actualResult = _account.toString();
        
        // _account.toString() should return the same string as expectedResult
        
        assertTrue ("Error in testToString", expectedResult.equals(actualResult));
    }
    
    // This will test if the equals method can properly compare accounts
    @Test
    public void testEquals()
    {
        Account account2 = new Account("Name", "001", new Money (0, 0));
        
        // Expected result is true
        
        assertTrue ("Error in testEquals", _account.equals(account2));
        
        Account account3 = new Account("Nam", "001", new Money (0, 0));
        Account account4 = new Account("Name", "00", new Money (0, 0));
        Account account5 = new Account("Name", "001", new Money (10, 0));
        
        // Expected result should be false for all 3 tests
        
        assertFalse ("Error in testEquals", _account.equals(account3));
        assertFalse ("Error in testEquals", _account.equals(account4));
        assertFalse ("Error in testEquals", _account.equals(account5));
    }
    
    @Test
    public void testPrivacyLeak()
    {
        //Test returnID
        String id2 = _account.returnID();
        
        id2 = "200";
        
        assertTrue ("Error in testPrivacyLeak", _account.returnID().equals("001"));
        
        //Test Constructor
        Money testMoney = new Money(10, 0);
        Account account2 = new Account("Name", "002", testMoney);
        
        testMoney.add(new Money(10,0));
        
        assertTrue("Error in testPrivacyLeak", account2.toString().equals("Name, 002, $10.00"));
    }
}
