Overview
The Builder Design Pattern is used to construct complex objects step by step, avoiding telescoping constructors and improving code readability. It is especially useful when an object has many optional parameters.

Initial Example: Initial.java
Later extended file as per requirment: future.java

Why Builder Pattern?

Avoids complex constructors with many parameters.
Improves readability using method chaining.
Makes objects immutable after creation.
Easy to extend for new features without breaking existing code.


Short Notes: Immutability

Definition: An immutable object’s state cannot be changed after creation.
How to achieve in Builder pattern:

Make fields final in the product class.
Do not provide setters in the product class.
Use a private constructor and build via the Builder.


Access: Implement getter methods separately for each field since setters are removed.
Benefits: Thread safety, predictability, and easier debugging.

Purpose:
Separates the construction of a complex object from its representation so the same construction process can create different representations.


Key Idea:
Instead of having a telescoping constructor with many parameters, use a Builder to construct the object step by step.


When to Use:

When an object has many optional parameters.
When you want to avoid complex constructors.
When you need immutable objects after creation.



Benefits:

Improves readability and maintainability.
Supports method chaining for cleaner code.
Allows different representations of the same object.
