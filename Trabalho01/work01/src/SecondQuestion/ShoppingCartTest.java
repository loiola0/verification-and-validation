package SecondQuestion;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingCartTest {
	
	@Test
	public void shouldWhenCreatedShoppingCartHasZeroItems() {
		ShoppingCart shoppingCart = new ShoppingCart();
		
		assertEquals(0, shoppingCart.getItemCount());
	}
	
	@Test
	public void shouldWhenEmptyShoppingCartHasZeroItems() throws ProductNotFoundException {
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Product shampoo = new Product("Shampoo", 14.99);
		
		shoppingCart.addItem(shampoo);
		
		shoppingCart.removeItem(shampoo);
		
		assertEquals(0, shoppingCart.getItemCount());
	}
	
	@Test
	public void shouldWhenProductIsAddedNumberItemsMustBeIncremented(){
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Product shampoo = new Product("Shampoo", 14.99);
		
		shoppingCart.addItem(shampoo);
		
		assertEquals(1, shoppingCart.getItemCount());
	}
	
	@Test
	public void WhenNewProductIsAddedNewBalanceMustBeTheSumPreviousBalancePlusCostNewProduct(){
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Product shampoo = new Product("Shampoo", 14.99);
		
		shoppingCart.addItem(shampoo);
		
		assertEquals(14.99, shoppingCart.getBalance());
	}
	
	@Test
	public void shouldWhenAnItemIsRemovedNumberitemsMustBeDecreased() throws ProductNotFoundException {
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Product shampoo = new Product("Shampoo", 14.99);
		
		shoppingCart.addItem(shampoo);
		
		Product car = new Product("Car", 100000);
		
		shoppingCart.addItem(car);
		
		shoppingCart.removeItem(car);
		
		assertEquals(1, shoppingCart.getItemCount());
	}
	
	@Test
	public void shouldWhenProductThatIsNotInCartIsRemoved() throws ProductNotFoundException {
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Product shampoo = new Product("Shampoo", 14.99);
		
		shoppingCart.addItem(shampoo);
		
		Product car = new Product("Car", 100000);
		
		
		assertThrows(ProductNotFoundException.class, () -> shoppingCart.removeItem(car));
	}
	
	
}
