[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/J_c8sizy)
# MassiveMotion
CS 245 Project 02 - Fall 2025

## Overview
This project implements a 2D celestial body simulation using Java Swing and AWT. The simulation creates and animates a star system with one central star (red) and multiple comets (black) that are generated at the edges of the canvas and move across the screen.

## Features
- **Multiple List Implementations**: Four different list data structures (ArrayList, LinkedList, DoublyLinkedList, DummyHeadLinkedList)
- **Configurable Simulation**: Property file-based configuration for all simulation parameters
- **Real-time Animation**: Smooth 2D animation using Java Swing Timer
- **Dynamic Object Generation**: Probabilistic generation of new celestial bodies at canvas edges
- **Object Lifecycle Management**: Automatic removal of objects that move off-screen

## Project Structure
```
src/
├── List.java                    # Interface defining list operations
├── ArrayList.java               # Array-based list implementation
├── LinkedList.java              # Singly-linked list implementation
├── DoublyLinkedList.java        # Doubly-linked list implementation
├── DummyHeadLinkedList.java     # Linked list with dummy head node
├── CelestialBody.java           # Class representing stars and comets
├── ListFactory.java             # Factory for creating list instances
└── MassiveMotion.java           # Main simulation class
```

## List Implementations

### ArrayList
- **Type**: Array-based list with initial capacity of 10
- **Time Complexity**: O(1) for get/set, O(n) for add/remove at arbitrary positions
- **Space Complexity**: O(n)
- **Best For**: Random access and frequent get operations

### LinkedList (Singly-linked)
- **Type**: Linked list with next pointers only
- **Time Complexity**: O(n) for get/set, O(1) for add/remove at known positions
- **Space Complexity**: O(n)
- **Best For**: Frequent insertions/deletions at known positions

### DoublyLinkedList
- **Type**: Linked list with both next and prev pointers
- **Time Complexity**: O(n) for get/set, O(1) for add/remove at known positions
- **Space Complexity**: O(n)
- **Best For**: Bidirectional traversal and frequent insertions/deletions

### DummyHeadLinkedList
- **Type**: Linked list with empty head node
- **Time Complexity**: O(n) for get/set, O(1) for add/remove at known positions
- **Space Complexity**: O(n)
- **Best For**: Simplified insertion logic at the beginning of the list

## Configuration
The simulation is configured via property files. Key parameters include:

- `timer_delay`: Canvas update frequency (milliseconds)
- `list`: List implementation type (arraylist, single, double, dummyhead)
- `window_size_x`, `window_size_y`: Canvas dimensions
- `star_position_x`, `star_position_y`: Initial star position
- `star_size`: Star radius in pixels
- `star_velocity_x`, `star_velocity_y`: Star movement velocity
- `gen_x`, `gen_y`: Probability of generating new bodies from edges
- `body_size`: Radius of generated comets
- `body_velocity`: Velocity range for generated comets

## Usage

### Compilation
```bash
javac -d . src/*.java
```

### Running the Simulation
```bash
java MassiveMotion <property_file>
```

### Example Commands
```bash
# Run with ArrayList implementation
java MassiveMotion MassiveMotion.txt

# Run with LinkedList implementation
java MassiveMotion MassiveMotion_linkedlist.txt

# Run with DoublyLinkedList implementation
java MassiveMotion MassiveMotion_doublylinkedlist.txt

# Run with DummyHeadLinkedList implementation
java MassiveMotion MassiveMotion_dummyhead.txt
```

## Implementation Details

### CelestialBody Class
- Encapsulates position, velocity, size, and color information
- Provides methods for position updates and boundary checking
- Distinguishes between stars (red) and comets (black)

### Simulation Loop
1. **Update Phase**: Move all celestial bodies based on their velocities
2. **Cleanup Phase**: Remove bodies that have moved off-screen
3. **Generation Phase**: Probabilistically generate new bodies at edges
4. **Render Phase**: Paint all bodies on the canvas

### Performance Considerations
- **Efficient Removal**: Iterate backwards through the list when removing out-of-bounds objects
- **Memory Management**: Objects are automatically garbage collected when removed
- **Animation Smoothness**: Timer delay can be adjusted for different performance requirements

## Testing and Verification

### Correctness Verification
- **Visual Verification**: Smooth animation with proper object movement
- **Boundary Testing**: Objects correctly removed when moving off-screen
- **Generation Testing**: New objects appear at correct edge positions
- **List Operations**: All list implementations pass basic functionality tests

### Performance Analysis
- **ArrayList**: Best for random access, O(1) get operations
- **LinkedList Variants**: Better for frequent insertions/deletions
- **Memory Usage**: All implementations use O(n) space
- **Scalability**: Performance degrades with very large numbers of objects

## Extensibility

### Potential Enhancements
1. **Gravity Simulation**: Add gravitational forces between objects
2. **Collision Detection**: Implement object collision and merging
3. **Object Types**: Add different types of celestial bodies (planets, asteroids)
4. **Visual Effects**: Add trails, particle effects, or background stars
5. **User Interaction**: Allow mouse/touch interaction with objects

### Design Patterns Used
- **Factory Pattern**: ListFactory for creating list instances
- **Strategy Pattern**: Different list implementations for different use cases
- **Observer Pattern**: Timer-based animation updates

## Code Quality
- **Javadoc Comments**: Comprehensive documentation for all public methods
- **Consistent Naming**: camelCase naming convention throughout
- **Error Handling**: Proper exception handling for file I/O and invalid inputs
- **Modular Design**: Clear separation of concerns between classes

## Author
Mir Ali Zain
