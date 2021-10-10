# Notes

Mockito libraries(mockito-junit-jupiter, mockito-core) added to mock the dependencies during unit tests.

Implemented a simple SinglePriceDiscount class which can be used to define 
all the discounts which can be applied on a single products e.g. Buy one get one free, three for the price of one, 
three for £1. Assumption is the SinglePriceDiscount will be created using the actual discount applied as it does not 
depends on cheapest items price while apply discounts.

To apply multiple discounts on a single products e.g.Pint of milk( £0.50 each, three for £1.30 or six for £2.40) for  
the Discounts calculator can be updated to calculate all the discounts 
on the specific items and add the maximum discount to total discount. 

Could not spend much time on applying single discount on a group of items.