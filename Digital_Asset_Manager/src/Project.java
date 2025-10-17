// Importing necessary libraries
import java.util.ArrayList;
import java.util.List;

/*
 * Creating an Interface to define Behavior
 * INTERFACE: Licensable
 * This defines a contract for any asset that can be activated.
 * Any class IMPLEMENTING this interface MUST provide a body for its methods.
 * */

interface Licensable{
    // creating Interface Methods
    // in Software_License. Java is case-sensitive, so the names must be identical!
    void activateLicense(String key);
}

/*
    Creating an Abstraction that defines Blueprint and Data
    * ABSTRACT CLASS: Asset
 * This serves as the base blueprint. It cannot be instantiated itself,
 * but it provides common structure for all its children (Software, Hardware).
 */

abstract class Asset{
    // Using Encapsulation to hide data making fields private
    private String Asset_ID;
    private String Name;
    // using protect to be only accessible to this class and it's sub class
    protected boolean is_operational;

    // Building constructor to initialize common data
    public  Asset(String Asset_ID, String Name){
        this.Asset_ID = Asset_ID;
        this.Name = Name;
        this.is_operational = true;
    }

    // Creating Getter Methods to allow controlled access to private data
    public String getName(){
        return Name;
    }
    public String getAsset_ID(){
        return Asset_ID;
    }
    // ABSTRACTION: Abstract method (no body here).
    // This FORCES all concrete child classes to provide their own implementation.
    public abstract String getDetails();
}

/*
Using Inheritance
* CONCRETE CLASS: SoftwareLicense
 * - INHERITS from Asset (Single Inheritance)
 * - IMPLEMENTS the Licensable interface (Java's multi-behavior solution)
 */

class Software_License extends Asset implements Licensable{
    // using Encapsulation to hide license key and platform
    private String license_key;
    private String platform;

    // Overloading constructor
    public Software_License(String Asset_Id, String name,String key, String platform){
        super(Asset_Id, name);
        this.license_key = key;
        this.platform = platform;
        System.out.println("SoftwareLicense created: " + name);
    }
    // Overload Again
    public Software_License(String assetId, String name, String key) {
        // Calls the first constructor to avoid code repetition (this() calls another constructor)
        this(assetId, name, key, "Windows");
    }

    // INTERFACE IMPLEMENTATION: Must provide a body for activateLicense()
    @Override
    public void activateLicense(String key) {
        // This method signature now matches the 'Licensable' interface.
        if (key.length() > 10) {
            this.license_key = key;
            System.out.println("License for " + getName() + " activated successfully.");
        } else {
            System.out.println("Error: Invalid license key.");
        }
    }

    // METHOD OVERRIDING: Providing the concrete implementation for the abstract method.
    @Override
    public String getDetails() {
        // Uses inherited data (getName(), getAssetId())
        // Note: is_operational is 'protected', so it's directly available here.
        String status = is_operational ? "Active" : "Decommissioned";
        return String.format("Software | ID: %s | Name: %s | Status: %s | Platform: %s",
                getAsset_ID(), getName(), status, this.platform);
    }
}

class HardwareDevice extends Asset {

    // ENCAPSULATION: Specific private data for Hardware
    private String modelNumber;
    private String location;

    public HardwareDevice(String assetId, String name, String model, String location) {
        super(assetId, name);
        this.modelNumber = model;
        this.location = location;
        System.out.println("HardwareDevice created: " + name);
    }

    // METHOD OVERRIDING: Providing the concrete implementation for the abstract method.
    // The implementation is completely different from SoftwareLicense's implementation.
    @Override
    public String getDetails() {
        // 🛠️ DEBUG FIX: Changed 'isOperational' (camelCase) to 'is_operational'
        // to match the protected field name defined in the parent 'Asset' class.
        String status = is_operational ? "In Use" : "In Storage";
        return String.format("Hardware | ID: %s | Name: %s | Status: %s | Model: %s | Location: %s",
                getAsset_ID(), getName(), status, this.modelNumber, this.location);
    }

    // Encapsulation Example: Setter to change the private location
    public void relocate(String newLocation) {
        this.location = newLocation;
        System.out.println(getName() + " relocated to " + newLocation);
    }
}

public class Project { // File must be named Project.java
    public static void main(String[] args){
        System.out.println("          Digital ASSET MANAGEMENT SYSTEM          ");

        // Create the concrete objects
        // to match the class name you defined.
        Software_License excel = new Software_License("SW1001", "MS Office", "XYZ-456-ABC");
        HardwareDevice server = new HardwareDevice("HW2050", "Main Server", "HP DL380", "Data Center 1");

        // --- DEMONSTRATING POLYMORPHISM ---
        // We use a list of the parent type (Asset) to hold objects of different child types.
        List<Asset> inventory = new ArrayList<>();
        inventory.add(excel); // Storing a SoftwareLicense object
        inventory.add(server); // Storing a HardwareDevice object

        System.out.println("\n--- 1. Calling Activated Behavior (Interface) ---");
        // We can only activate the Licensable object (excel)
        // Note: The 'activateLicense' method was inherited via the Interface.
        excel.activateLicense("VALID-KEY-2025");

        System.out.println("\n--- 2. Looping Through Inventory (Overriding & Polymorphism) ---");

        // The loop is iterating over 'Asset' types.
        for (Asset item : inventory) {
            // Polymorphism: When we call getDetails(), the JVM automatically knows
            // to call the specific version of the method defined in the actual object
            // (either SoftwareLicense's or HardwareDevice's version).
            System.out.println(item.getDetails());
        }

        System.out.println("\n--- 3. Using Specific Instance Methods ---");
        // Calling a method unique to HardwareDevice, proving the object is still a HardwareDevice.
        server.relocate("Storage Room B");

        System.out.println("\n==================================================");
    }
}
