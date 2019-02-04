# Checkout Order Total

This project's goal is to showcase the use of Test Driven Development and Atomic commits to implement the business logic for a grocery point-of-sale system. This project was built with Java 1.8, JUnit 4.12, and Gradle 4.10. As this project is only business logic it does not have any UI or command line interface. It can only run the unit tests and show the results

## Dependencies

Java version 1.8 or greater is installed on users computer

## Run Unit Tests
```
gradlew test
```
Complete test results can be viewed in the html generated report located at: build/reports/tests/test/index.html

## The Task

- Accept a scanned item. The total should reflect an increase by the eaches price after the scan. You will need a way to configure the prices of scannable items prior to being scanned.
- Accept a scanned item and a weight. The total should reflect an increase of the price of the item for the given weight.
- Support a markdown. A marked-down item will reflect the eaches cost less the markdown when scanned. A weighted item with a markdown will reflect that reduction in cost per unit.
- Support a special in the form of "Buy N items get M at %X off." For example, "Buy 1 get 1 free" or "Buy 2 get 1 half off."
- Support a special in the form of "N for $X." For example, "3 for $5.00"
- Support a limit on specials, for example, "buy 2 get 1 free, limit 6" would prevent getting a third free item.
- Support removing a scanned item, keeping the total correct after possibly invalidating a special.
- Support "Buy N, get M of equal or lesser value for %X off" on weighted items.

## Constraints on current solution

In order to scope this open ended task into a deliverable solution the following constraints have been applied. 

- The API will be used in "good faith" for its intended functionality.
- All items, markdowns, and specials are entered into the product catalog before a checkout occurs and can not be altered in the middle of a checkout.
- 1 special or markdown per item.
- Special takes priority over markdown. (does not stack)
- Markdowns and specials will not result in a negative cost item.
- Eaches items will not have specials of fractional amounts.
- When calculating costs of an item the fractional amount of a 1/10th of a penny is always rounded up to a whole penny.
- Special limits are defined as the amount of time a stated special is applied not the number of items in a special. Ex. Buy 1 get 2 results means buying 3 items counts as 1 against the limit. 
- All weighted items are defined as "price per 1 unit" and cannot be "price per 1/2 unit".
- There is currently no clear register API so application must be restarted or all items manually removed to start a new order.

If any of these constraints on the current solution do not meet user needs, a feature request can be submitted
