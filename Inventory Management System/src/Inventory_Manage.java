public class Inventory_Manage {
    // Creating a public class named ProductInventory
    public static class ProductInventory{
        // creating a private array to store product names
        private  String[] product_names = new String[10]; // keeping limit to 10
        // Creating a set method to add a product to specific index
        public void add_Product(String name, int index){
            // using exception handling to tackle unwanted errors
            try{
                product_names[index] = name;
                System.out.println("Product added: " + name + " at index " + index);
                // Possibility for ArrayIndexOutOfBoundsException
        }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Error: Invalid Index " + index + ". Valid range is 0 to 9");
            }
        }
        // Creating a get method to see product of a specific index
        public void getProduct(int index){
            // Again using exception Handling to avoid errors
            try{
                String product = product_names[index];
                // using conditional statements
                if (product == null){
                    System.out.println("No product found at index " + index);
                } else {
                    System.out.println("Product at index " + index + ": " + product);
                }
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Error: Invalid index " + index + ". Valid range is 0 to 9.");
            }
        }
    }
    public static void main(String[] args){
        ProductInventory inv = new ProductInventory();
        // let's add some product
        inv.add_Product("Laptop", 0);
        inv.add_Product("Phone", 1);
        inv.add_Product("wireless keyboard", 2);
        inv.add_Product("wireless mouse", 3);
        inv.add_Product("mouse pad", 4);
        inv.add_Product("portable SSD", 5);
        inv.add_Product("Phone Charger", 6);
        inv.add_Product("Laptop Charger", 7);
        inv.add_Product("Power Bank", 8);
        inv.add_Product("Pocket Router", 9);
        // let's get them
        inv.getProduct(0);
        inv.getProduct(6);
        inv.getProduct(9);

    }
}
