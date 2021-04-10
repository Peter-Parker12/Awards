package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.CoffeeMaker;
import edu.ncsu.csc326.coffeemaker.Main;
import edu.ncsu.csc326.coffeemaker.Recipe;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
public class TestGolden {
	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker} 
	 * object we wish to test.
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();
		
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
		
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
	}
	
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
	}
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative 
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "asdf", "3");
	}
	
	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than 
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	
	//Add an invalid recipe
	@Test (expected = RecipeException.class)
	public void testAddRecipe1() throws RecipeException{
		Recipe sample = new Recipe();
		
		sample.setAmtChocolate("10");
		sample.setAmtCoffee("10");
		sample.setAmtMilk("10");
		sample.setAmtSugar("10");
		
		sample.setName("   ");
		sample.setPrice("1.9");
		
		coffeeMaker.editRecipe(0, sample);
	}
	
	//Add an invalid recipe v-2
	@Test (expected = RecipeException.class)
	public void testAddRecipe2() throws RecipeException{
		Recipe sample = new Recipe();
		
		sample.setAmtChocolate("-1");
		sample.setAmtCoffee("0");
		sample.setAmtMilk("0");
		sample.setAmtSugar("20");
		sample.setPrice("a");
		
		coffeeMaker.addRecipe(sample);
	}
	
	//Insert a recipe while the queue is full
	@Test 
	public void testInsertRecipe1() throws RecipeException{
		Recipe sample = new Recipe();
		
		sample.setAmtChocolate("10");
		sample.setAmtCoffee("10");
		sample.setAmtMilk("10");
		sample.setAmtSugar("10");
		sample.setPrice("10");
		sample.setName("The besr coffee in the world");
				
		assertFalse((coffeeMaker.addRecipe(sample)));
	}
	
	//Insert a recipe into the queue
	@Test
	public void testInsertRecipe2() throws RecipeException{
		Recipe sample = new Recipe();
		
		sample.setAmtChocolate("10");
		sample.setAmtCoffee("10");
		sample.setAmtMilk("10");
		sample.setAmtSugar("10");
		sample.setPrice("10");
		sample.setName("The best coffee in the world");
		
		coffeeMaker.deleteRecipe(0);
		assertTrue(coffeeMaker.addRecipe(sample));
	}
	
	//Insert a recipe already in the recipe book
	@Test
	public void testInsertRecipe3() throws RecipeException{
		Recipe sample = new Recipe();
		
		sample.setAmtChocolate("10");
		sample.setAmtCoffee("10");
		sample.setAmtMilk("10");
		sample.setAmtSugar("10");
		sample.setPrice("10");
		sample.setName("The best coffee in the world");
		
		coffeeMaker.deleteRecipe(1);
		
		coffeeMaker.addRecipe(sample);
		assertFalse(coffeeMaker.addRecipe(sample));
	}
	
	//Delete a recipe in the queue
	@Test
	public void testDeleteRecipe1() {
		assertEquals("Coffee", coffeeMaker.deleteRecipe(0));
	}
	
	//Delete a recipe not in the queue
	@Test
	public void testDeleteRecipe2() {
		assertEquals(null, coffeeMaker.deleteRecipe(-1));
	}
	
	//Delete a recipe twice
	@Test
	public void testDeleteRecipe3() {
		coffeeMaker.deleteRecipe(0);
		assertEquals(null, coffeeMaker.deleteRecipe(0));
	}
	
	//Delete a emtpy name recipe
	@Test
	public void testDeleteRecipe4() {
		Recipe sample = new Recipe();
		
		coffeeMaker.deleteRecipe(0);
		coffeeMaker.addRecipe(sample);
		
		assertEquals("", coffeeMaker.deleteRecipe(0));
	}
	
	//Edit a non-existing recipe
	@Test
	public void testEditRecipe1() throws RecipeException{
		Recipe sample = new Recipe();
		
		sample.setAmtChocolate("10");
		sample.setAmtCoffee("10");
		sample.setAmtMilk("10");
		sample.setAmtSugar("10");
		sample.setPrice("20");
		sample.setName("Espresso");
		
		assertEquals(null, coffeeMaker.editRecipe(3, sample));
	}
	
	//Edit a non-existing recipe v-2
	@Test
	public void testEditRecipe() {
		Recipe sample = new Recipe();
		
		assertEquals(null, coffeeMaker.editRecipe(-1, sample));
	}
	
	//Edit a correct recipe
	@Test
	public void testEditRecipe2() throws RecipeException{
		Recipe  sample = new Recipe();
		
		sample.setAmtChocolate("2");
		sample.setAmtCoffee("3");
		sample.setAmtMilk("4");
		sample.setAmtSugar("1");
		sample.setName("Milk-shake");
		sample.setPrice("9");
		
		assertEquals("Coffee", coffeeMaker.editRecipe(0, sample));
	}
	
	//Edit a null recipe
	@Test
	public void testEditRecipe3() throws RecipeException{
		coffeeMaker.deleteRecipe(0);
		assertEquals(null, coffeeMaker.editRecipe(0, new Recipe()));		
	}
	
	//check the current inventory after added a valid inventory
	@Test
	public void tesCheckInventory1() throws InventoryException{
		String output = "Coffee: 35\n" +"Milk: 25\n" + "Sugar: 30\n" + "Chocolate: 31\n";
		coffeeMaker.addInventory("20", "10", "15", "16");
		assertEquals(output, coffeeMaker.checkInventory());
	}
	
	//Check the inventory after added an invalid inventory
	@Test
	public void testCheckInventory2() {
		String output = "Coffee: 20\n";
	}
	
	
	//test make a valid cup of coffee
	@Test
	public void testMakeCoffee1() throws InventoryException{
		coffeeMaker.addInventory("10", "10", "10", "10");
		assertEquals(50, coffeeMaker.makeCoffee(0, 100));
	}
	
	//make a cup of coffee from non-existing recipe
	@Test
	public void testMakeCoffee2() {
		assertEquals(100, coffeeMaker.makeCoffee(-1, 100));
	}
	
	//make a cup of coffee with insufficient money
	@Test
	public void testMakeCoffee3() {
		assertEquals(25, coffeeMaker.makeCoffee(1, 25));
	}
	
	//make a cup of coffee from a recipe not in the list
	@Test
	public void testMakeCoffee4() {
		coffeeMaker.deleteRecipe(0);
		assertEquals(100, coffeeMaker.makeCoffee(0, 100));
	}
	
	//make a cup of coffee with insufficient ingredient
	@Test
	public void testMakeCoffee5() throws RecipeException{
		Recipe sample = new Recipe();
		
		sample.setAmtChocolate("20");
		sample.setAmtCoffee("20");
		sample.setAmtMilk("20");
		sample.setAmtSugar("20");
		sample.setName("One");
		sample.setPrice("10");
		
		coffeeMaker.editRecipe(0, sample);
		assertEquals(100, coffeeMaker.makeCoffee(0, 100));
	}
	
	//make a non-existing cup of coffee
	@Test
	public void testMakeCoffee6() {
		assertEquals(100, coffeeMaker.makeCoffee(3, 100));
	}
	
	//make a cup of coffee with wrong input
	@Test
	public void testMakeCoffee7() {
		assertEquals(-1, coffeeMaker.makeCoffee(0, -1));
	}
	
	//Test running main
//	@Test
//	public void testMainMethod() {
//		String input = "1\n";
//		
//		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
//		System.setIn(in);
//		
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(out));
//		
//		String consoleOutput="";
//		
//		String[] args = {};
//		
//		Main.main(args);
//		
//		assertEquals(consoleOutput, out.toString());
//	}
	
}
