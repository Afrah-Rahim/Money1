import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// Test the Money class. 

public class MoneyTest 
{
    private Money _amount;

    /**
     * Default constructor for test class MoneyTest
     */
    public MoneyTest()
    {
        //System.out.println("JUnit Framework calls Constructor of test class before executing test methods");
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        _amount = new Money(0, 0);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        _amount = null;
    }

    /**
     * Test methods 
     */
    
    // Test creation of Money objects.
    @Test
    public void testCreate()
    {
         assertEquals("Error in testCreate", 0, _amount.getDollars());
         assertEquals("Error in testCreate", 0, _amount.getCents());
         
         Money amount2 = new Money (4, 50);
         
         assertEquals("Error in testCreate", 4, amount2.getDollars());
         assertEquals("Error in testCreate", 50, amount2.getCents());
         
         Money amount3 = new Money (-4, -50);
         
         assertEquals("Error in testCreate", -4, amount3.getDollars());
         assertEquals("Error in testCreate", -50, amount3.getCents());
         
    }
   
    // Test conversion of Money object to String.
    @Test
    public void testToString()
    {
        // First test: cents is two digits
        Money amount = new Money (7, 55);
        String actual = amount.toString();
        String expected = "$7.55";
        assertTrue("Error in testToString", actual.equals(expected));
        
        // Second test: cents is one digit
        Money amount2 = new Money (7, 5);
        String actual2 = amount2.toString();
        String expected2 = "$7.05";
        assertTrue("Error in testToString", actual2.equals(expected2));
    }
    
    // Test equality of money values that are the same.
    @Test
    public void testEquality()
    {
        Money myCash = new Money (3, 75);
        Money yourCash = new Money (3, 75);
        
        assertTrue ("Error in testEquality", myCash.equals(yourCash));
        
        Money myAmount = new Money (50, 0);
        Money yourAmount = new Money (50, 0);
        
        assertTrue ("Error in testEquality", myAmount.equals(yourAmount));
    }
    
    // Test inequality of money values that are different.
    @Test
    public void testInequality()
    {
        Money myCash = new Money (3, 75);
        Money difftDollarsSameCents = new Money (4, 75);    
        Money difftDollarsDifftCents = new Money (4, 80);   
        Money sameDollarsDifftCents = new Money (3, 80);   
        
        assertFalse ("Error in testInequality", myCash.equals(difftDollarsSameCents));
        assertFalse ("Error in testInequality", myCash.equals(difftDollarsDifftCents));
        assertFalse ("Error in testInequality", myCash.equals(sameDollarsDifftCents));
    }
    
    // Test addition of money values such that the sum of the cents do not exceed 99.
    @Test
    public void testSimpleAdd()
    {
       Money amount2 = new Money (2, 30);
       Money amount3 = new Money (3, 50);
       
       Money actualAmount = amount2.add (amount3);
       // actualAmount now has the sum of amount2 + amount 3
       
       // Expected result is $5.80
       Money expectedAmount = new Money (5, 80);
       
       assertTrue ("Error in testSimpleAdd", actualAmount.equals(expectedAmount));
       //assertEquals("Error in testSimpleAdd", 5, actualAmount.getDollars());
       //assertEquals("Error in testSimpleAdd", 80, actualAmount.getCents());
    }
    
    // Test complex addition  of two money values, i.e. sum of cents is greater than or equal to 100.
    @Test
    public void testComplexAdd()
    {
        // First test: sum of cents is 100.
        
        Money myCash = new Money (3, 50);
        Money yourCash = new Money (4, 50);            
        
        // Expected result is $8.00
        Money expectedAmount = new Money (8, 0);
       
        Money actualAmount = myCash.add(yourCash);
        
        //System.out.println (actualAmount.toString()); // just for tracing purposes
        
        assertTrue ("Error in testComplexAdd", actualAmount.equals(expectedAmount));     
        
        // Second test: sum of cents is greater than 100...
        
        Money myCash2 = new Money (4, 60);
        Money yourCash2 = new Money (5, 80);
        
        // Expected result is $10.40
        Money expectedAmount2 = new Money (10, 40);
        
        Money actualAmount2 = myCash2.add(yourCash2);
        
        assertTrue ("Error in testComplexAdd", actualAmount2.equals(expectedAmount2));
        
    }
    
    // This will test the subtraction of two Money objects
    @Test
    public void testSubtract()
    {
        Money cash1 = new Money (4, 50);
        Money cash2 = new Money (3, 50);
        
        // Expected result is $1.00
        Money expectedAmount = new Money (1, 0);
        
        Money actualAmount = cash1.subtract(cash2);
        
        assertTrue ("Error in testSubtract", actualAmount.equals(expectedAmount));
        
        Money cash3 = new Money (4, 70);
        Money cash4 = new Money (4, 50);
        
        // Expected result is $1.00
        Money expectedAmount2 = new Money (0, 20);
        
        Money actualAmount2 = cash3.subtract(cash4);
        
        assertTrue ("Error in testSubtract", actualAmount2.equals(expectedAmount2));
    }
    
    // This will test the compareTo method 3 times for each different result
    @Test
    public void testCompare()
    {
        Money testCash = new Money (3, 50);
        Money cash1 = new Money (3, 50);
        Money cash2 = new Money (4, 50);
        Money cash3 = new Money (2, 50);
        
        // 1st Test: If they are the same
        // Expected result is 0
        
        assertEquals ("Error in testCompare", 0, testCash.compareTo(cash1));
        
        // 2nd Test: If testCash is less than the parameter, cash2
        // Expected result is -1
        
        assertEquals ("Error in testCompare", -1, testCash.compareTo(cash2));
        
        // 2nd Test: If testCash is greater than the parameter, cash3
        // Expected result is 1
        
        assertEquals ("Error in testCompare", 1, testCash.compareTo(cash3));
    }
    
    

}



