# Wherehoused-back-end
Skillstorm project 1 backend files

Wherehoused tables
Warehouse
Shelf (refers to the Warehouse it exists in)
shelfItems (many-many relationship table for Shelves and Items)
Item (stored on one or multiple assocations to difference shelves in shelfItems)

Functional Requirements
  CRUD functionality
  Can not move items into full Warehouses
    - Shelf has a capacity (1000)
    - Item stored reduces Shelf capacity by Item.size * Item.quantity
      - Can not reduce below 0
    - (SOLUTION) Warehouses with only full shelves reject all requests to associate with more items
