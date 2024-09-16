import java.util.*;
import java.io.*;

public class Product {
	private String category;
	private String productID;
	private String productName;
	private String description;
	private double productPrice;
	private int productQuantity;
	
    public Product() {
    	this.category = "";
    	this.productID = "";
    	this.productName = "";
    	this.description = "";
    	this.productPrice = 0.0;
    	this.productQuantity = 0;
    }
    
    public Product(String category, String productID, String productName, String description, double productPrice, int productQuantity){
    	this.category = category;
    	this.productID = productID;
    	this.productName = productName;
    	this.description = description;
    	this.productPrice = productPrice;
    	this.productQuantity = productQuantity;
    }
     
    public String getCategory(){
    	return category;
    }
    
    public void setCategory(String category){
    	this.category = category;
    }
    
   	public String getProductID(){
   		return productID;
   	}
   	
   	public void setProductID(String productID){
   		this.productID = productID;
   	}
   	
   	public String getProductName(){
   		return productName;
   	}
   	
   	public void setProductName(String productName){
   		this.productName = productName;
   	}
    
    public String getDescription(){
    	return description;
    }
    
    public void setDescription(String description){
    	this.description = description;
    }
    
    public double getProductPrice(){
    	return productPrice;
    }
    
    public void setProductPrice(double productPrice){
    	this.productPrice = productPrice;
    }
    
    public int getProductQuantity(){
    	return productQuantity;
    }
    
    public void setProductQuantity(int productQuantity){
    	this.productQuantity = productQuantity;
    }
   
    
    public void productMenu(){
    	Scanner scanner = new Scanner(System.in);
    	String inputProductID;
    	int selection = 0;
    	while(selection != 7){
    		System.out.println("-----------------------------");
    		System.out.println("\tPRODUCT MENU	    ");
    		System.out.println("-----------------------------\n");
    		System.out.println("|---------------------------|");
	    	System.out.println("|Select an option.	    |");
	    	System.out.println("|---------------------------|");
	    	System.out.println("|[1] Add new product	    |");
	    	System.out.println("|[2] Search product	    |");
	    	System.out.println("|[3] Update product price   |");
	    	System.out.println("|[4] Update product quantity|");
	    	System.out.println("|[5] Delete product         |");
	    	System.out.println("|[6] Generate report	    |");
	    	System.out.println("|[7] Exit		    |");
	    	System.out.println("|---------------------------|\n");
	    	System.out.print("Option: ");
	    	try{
	    		selection = scanner.nextInt();
	    		scanner.nextLine();
	    		switch(selection){
	    			case 1:
	    				addProduct();
	    				break;
	    			case 2:
	    				System.out.print("Enter product ID to display: ");
	    				inputProductID = scanner.nextLine().toUpperCase();
	    				displayProduct(inputProductID);
	    				break;
	    			case 3:
	    				System.out.print("Enter product ID to update price: ");
	    				inputProductID = scanner.nextLine().toUpperCase();
	    				System.out.println("\n");
	    				updateProductPrice(inputProductID);
	    				break;
	    			case 4:
	    				System.out.print("Enter product ID to update quantity: ");
	    				inputProductID = scanner.nextLine().toUpperCase();
	    				System.out.println("\n");
	    				updateProductQuantity(inputProductID);
	    				break;
	    			case 5:
	    				System.out.print("Enter product ID to delete product: ");
	    				inputProductID = scanner.nextLine().toUpperCase();
	    				System.out.println("\n\n");
	    				deleteProduct(inputProductID);
	    				break;
	    			case 6:
	    				generateReportProduct();
	    				break;
	    			case 7:
	    				System.out.println("Exiting system...");
	    				return;
	    		}
	    	} catch (InputMismatchException e){
	    		e.printStackTrace();
	    		scanner.nextLine();
	    	}	
	    }
    }
    
    public void addProduct(){
    	String inputCategory = "", inputProductID = "", inputProductName = "", inputDescription = "";
    	double inputProductPrice = 0.0;
    	int inputProductQuantity = 0;
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("\n\t---------------");
    	System.out.println("\tADD NEW PRODUCT");	
		System.out.println("\t---------------\n");
    	do{
    		System.out.print("Enter Product Category: ");
    		inputCategory = scanner.nextLine();
    		if (!inputCategory.equals("Stationary") && !inputCategory.equals("Paper Products") && !inputCategory.equals("Tools")){
     			System.out.println("Invalid Product Category.\nProduct Categories:\n1. Stationary\t2. Paper Products\t3. Tools");   			
    		} 
    	} while(!inputCategory.equals("Stationary") && !inputCategory.equals("Paper Products") && !inputCategory.equals("Tools"));		
    	setCategory(inputCategory);	
    	
    	do{
    		System.out.print("Enter new Product ID: ");
			inputProductID = scanner.nextLine().toUpperCase();
			if (!validateID(inputProductID, category)){
				System.out.println("Product ID is not valid.");
			} else if (!validateIDExists(inputProductID)){
				System.out.println("Product ID already existed.");
			}
    	} while(!validateID(inputProductID, category) || !validateIDExists(inputProductID));
    	setProductID(inputProductID);
    	
    	do{
    		System.out.print("Enter Product Name: ");
    		inputProductName = scanner.nextLine();
    		if (!validateName(inputProductName)){
    			System.out.println("Product name only allows characters and numbers.");
    		}    	
    	} while(!validateName(inputProductName));
    	setProductName(inputProductName);
    	
    	System.out.print("Enter Product Description: ");
    	inputDescription = scanner.nextLine();
    	setDescription(inputDescription);
    	
    	while(true){
    		System.out.print("Enter Product Price: "); 		
    		try {
    			inputProductPrice = scanner.nextDouble();
    			scanner.nextLine();
    			if (inputProductPrice > 0){
    				break;
    			} else {
    				System.out.println("Price must be more than RM 0.00.");
    			}
    		} catch (InputMismatchException e) {
    			e.printStackTrace();
    			scanner.nextLine();
    		}
    	}
    	setProductPrice(inputProductPrice);
    	
    	while(true){
    		System.out.print("Enter Product Quantity: ");
    		try	{
    			inputProductQuantity = scanner.nextInt();
   				scanner.nextLine();
   				if(inputProductQuantity > 0){
   					break;
   				} else {
   					System.out.println("Invalid quantity. Quantity must be more than 0.");
   				}
    		} catch (InputMismatchException e){
    			e.printStackTrace();
    			scanner.nextLine();
    		}
    	}
    	setProductQuantity(inputProductQuantity);

    	storeProductToFile();
    	System.out.println("----------------------------------------------------");
    	System.out.println("Product added successfully! Returning to menu...\n\n");
    }
    
    public void displayProduct(String productID) {
    	boolean foundProduct = false;
    	
    	System.out.println("\n\t|---------------|");
    	System.out.println("\t|PRODUCT DISPLAY|");	
		System.out.println("\t|---------------|");
    	try (Scanner readFile = new Scanner(new FileReader("products.txt"))) {
       		while (readFile.hasNextLine()) {
            	String lineID = readFile.nextLine().trim();
            	if (lineID.equals("Product ID: " + productID)) {
            		foundProduct = true;
                	System.out.println("\n" + lineID);
               		while (readFile.hasNextLine()) {
                    	String productLine = readFile.nextLine().trim();
                    	if (productLine.equals("--")) {
                        	break;
                    	}
                    	System.out.println(productLine);
                	}
            		System.out.println("\n\n");
            	}
        	}
        	if (!foundProduct){
        		System.out.println("Product with ID " + productID + " not found. Returning to menu...\n\n");
        		productMenu();
        	}
    	} catch (IOException e) {
        	e.printStackTrace();
    	}
	}
	
	public void updateProductPrice(String productID) {
	    Scanner scanner = new Scanner(System.in);
	    List<String> fileContent = new ArrayList<>();
	    boolean foundProduct = false;
	    double newPrice = 0.0;
	    
	    System.out.println("\n\t------------------------------------");
    	System.out.println("\t\tUPDATE PRODUCT PRICE");	
		System.out.println("\t------------------------------------\n");
	    		   	
	    try (BufferedReader fileReader = new BufferedReader(new FileReader("products.txt"))) {
	        String line;
	        while ((line = fileReader.readLine()) != null) {
	            fileContent.add(line.trim()); 
	            if (line.trim().equals("Product ID: " + productID)) {
	                foundProduct = true;
	                while ((line = fileReader.readLine()) != null && !line.trim().equals("--")) {
	                    fileContent.add(line.trim());
	                    if (line.trim().startsWith("Product Name: ")){
	                    	String productName = line.trim().substring(13);
	                    	System.out.println("Product Name: " + productName);
	                    }
	                    if (line.trim().startsWith("Price: ")) {
	                    	String productPrice = line.trim().substring(7);
	                    	System.out.println("Product Price: " + productPrice);
	                    	System.out.println("------------------------------------------");
	                        break;
	                    }
	                }
	                while(true){
				    	System.out.print("Enter new price for the product: ");
					    try {
					        newPrice = scanner.nextDouble();
					        scanner.nextLine();
					        if (newPrice > 0){
					        	break;
					        } else{
					        	System.out.println("Price must be more than RM 0.00	.");
					        }
					    } catch (InputMismatchException e) {
					        e.printStackTrace();
					        scanner.nextLine();
					        return;
					    }
				    }            
	                fileContent.remove(fileContent.size() - 1);
	                fileContent.add("Price: RM" + String.format("%.2f", newPrice));
	            }
	        }
	
	        if (!foundProduct) {
	            System.out.println("Product with ID " + productID + " not found.\n\n");
	            return;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"))) {
	        for (String contentLine : fileContent) {
	            writer.write(contentLine);
	            writer.newLine();
	        }
	        System.out.println("Product price updated successfully.\n\n");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    
    public void updateProductQuantity(String productID){
		Scanner scanner = new Scanner(System.in);
		List<String> fileContent = new ArrayList<>();
		boolean foundProduct = false;
		int currentQuantity = 0, amount = 0, newQuantity = 0;
		
		System.out.println("\n\t---------------------------------------");
    	System.out.println("\t\tUPDATE PRODUCT QUANTITY");	
		System.out.println("\t---------------------------------------\n");
		
		try (BufferedReader fileReader = new BufferedReader(new FileReader("products.txt"))){
			String line;
			while ((line = fileReader.readLine()) != null){
				fileContent.add(line.trim());
				if (line.equals("Product ID: " + productID)){
					foundProduct = true;
					while((line = fileReader.readLine()) != null && !line.trim().equals("--")){
						fileContent.add(line.trim());
						if(line.trim().startsWith("Product Name: ")){
							String productName = line.trim().substring(13);
							System.out.println("Product Name: " + productName);
						}
						if (line.trim().startsWith("Quantity: ")){
							currentQuantity = Integer.parseInt(line.trim().substring(10));
							System.out.println("Product Quantity: " + currentQuantity);
							System.out.println("------------------------------------------");
							break;
						}
					}
					System.out.print("Do you want to add stocks or reduce stocks? (add/reduce): ");
					String choice = scanner.nextLine().toLowerCase();
					System.out.print("Enter stocks to " + choice + ": ");
					try {
						amount = scanner.nextInt();
						scanner.nextLine();
						if (amount < 0){
							System.out.println("Please enter amount more than 0.");
							return;
						}
						if (choice.equals("add")){
							newQuantity = currentQuantity + amount;
						} else if(choice.equals("reduce")){
							newQuantity = currentQuantity - amount;
							
							if (newQuantity < 0){
								System.out.println("Quantity cannot be negative.");
                            	return;
							}
							
						} else{
							System.out.println("Invalid action. Please enter add or reduce only.");
							return;
						}
					} catch (InputMismatchException e){
						System.out.println("Invalid input. Please enter a valid number.");
                    	scanner.nextLine();
                    	return;
					}
					fileContent.remove(fileContent.size() - 1);
                	fileContent.add("Quantity: " + newQuantity);
				}
			}
			if (!foundProduct){
				System.out.println("Product with ID " + productID + " not found.");
	            return;	
			}
			
		} catch (IOException e){
			e.printStackTrace();
		}
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"))) {
	        for (String contentLine : fileContent) {
	            writer.write(contentLine);
	            writer.newLine();
	        }
	        System.out.println("Product quantity updated successfully.");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
    
    public void deleteProduct(String productID) {
    	Scanner scanner = new Scanner(System.in);
        List<String> fileContent = new ArrayList<>();
        boolean foundProduct = false;
        
        System.out.println("\n\t--------------------------------");
    	System.out.println("\t\tDELETE PRODUCT");	
		System.out.println("\t--------------------------------\n");
        
        try (Scanner readFile = new Scanner(new FileReader("products.txt"))) {
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                if (line.equals("Product ID: " + productID)) {
                    foundProduct = true;
                    if (readFile.hasNextLine()) {
                    	readFile.nextLine();
                        String nameLine = readFile.nextLine().trim();                  
	                    System.out.println("Product found:");
	                    System.out.println(line);
	                    System.out.println(nameLine);
                    }
                    System.out.println("------------------------------------------");
                    System.out.print("Are you sure you want to delete this product? (Y/N): ");
                    char choice = scanner.next().charAt(0);
                    
                    if (choice == 'Y' || choice == 'y'){
                    	System.out.println("Deleting product..");
                    	while (readFile.hasNextLine()) {
                            if (readFile.nextLine().equals("--")) {
                                break;
                            }
                        }
                    } else {
                    	System.out.println("Product delete process canceled.\n\n");
                    	return;
                    }
        
                } else {
                    fileContent.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (foundProduct) {
            try (PrintWriter writeFile = new PrintWriter(new FileWriter("products.txt"))) {
                for (String line : fileContent) {
                    writeFile.println(line);
                }
                System.out.println("Product with ID " + productID + " has been deleted.\n\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Product with ID " + productID + " not found.\n\n");
        }
    }
    
    public void generateReportProduct(){
    	List<String> fileContent = new ArrayList<>();
    	int totalProducts = 0, totalQuantity = 0;
    	double totalValue = 0.0;
    	
    	System.out.println("\n\t--------------------------------");
    	System.out.println("\t\tPRODUCT REPORT");	
		System.out.println("\t--------------------------------\n\n");
		
		try (BufferedReader fileReader = new BufferedReader(new FileReader("products.txt"))){
			String line;
			System.out.printf("%-12s %-15s %-14s %-10s %-10s %-20s\n", "Product ID", "Name", "Category", "Price (RM)", "Quantity", "Description");
        	System.out.println("--------------------------------------------------------------------------------------------------");
			while ((line = fileReader.readLine()) != null){
				if (line.startsWith("Product ID: ")){
					String productID = line.substring(12);
					String category = fileReader.readLine().substring(10);
                	String productName = fileReader.readLine().substring(13);
                	String description = fileReader.readLine().substring(13);
                	String priceString = fileReader.readLine().substring(7).replace("RM", "").trim();
                	double price = Double.parseDouble(priceString);
                	String quantityString = fileReader.readLine().substring(10);
                	int quantity = Integer.parseInt(quantityString);
                	
                	System.out.printf("%-11s %-15s %-15s RM%-9.2f %-10d %-20s\n", productID, productName, category, price, quantity, description);
                	totalProducts++;
                	totalQuantity += quantity;
                	totalValue += price * quantity;
				}
			}
			System.out.println("\nSummary");
			System.out.println("------------");
			System.out.println("Total Products: " + totalProducts);
        	System.out.println("Total Stock Quantity: " + totalQuantity);
        	System.out.printf("Total Inventory Value: RM%.2f\n\n\n", totalValue);
		} catch (IOException e){
			e.printStackTrace();
		}
    	
    }
    
    public double calculateTotalProductPrice(String productID, int productQuantity, double productPrice){
    	double totalProductPrice = 0.0;
    	return totalProductPrice;
    }
    
    private boolean validateID(String inputProductID, String category){
    	switch(category){
    		case "Stationary":
    			if (inputProductID.matches("^ST\\d{2}$")){
    				return true;
    			}
    			break;
    		case "Paper Products":
    			if (inputProductID.matches("^PP\\d{2}$")){
    				return true;
    			}
    			break;
    		case "Tools":
    			if (inputProductID.matches("^TO\\d{2}$")){
    				return true;
    			}
    			break;
    		default:
    			System.out.println("Unknown category: " + category);
    			return false;
    	}
    	return false;
    }
    
    private boolean validateIDExists(String inputProductID){
    	try (Scanner readFile = new Scanner(new FileReader("products.txt"))) {
       		while (readFile.hasNextLine()){
       			String line = readFile.nextLine().trim();
       			if (line.startsWith("Product ID: ")){
       				String checkID = line.replace("Product ID: ", "").trim();
       				if (checkID.equals(inputProductID)){
       					return false;
       				}
       			}
       		}
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return true;
    }
    
    private boolean validateName(String inputProductName){
    	char[] charProductName = inputProductName.toCharArray();
    	for (char length: charProductName){
    		if (!Character.isLetterOrDigit(length) && length != ' '){
    			return false;
    		}
    	}
    	return true;
    }

    private void storeProductToFile(){
    	try (FileWriter productFile = new FileWriter("products.txt", true)){
    		productFile.write("Product ID: " + productID + "\n");
            productFile.write("Category: " + category + "\n");
            productFile.write("Product Name: " + productName + "\n");
            productFile.write("Description: " + description + "\n");
            productFile.write("Price: RM" + String.format("%.2f", productPrice) + "\n");
            productFile.write("Quantity: " + productQuantity + "\n");
            productFile.write("--\n");
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    }
}
