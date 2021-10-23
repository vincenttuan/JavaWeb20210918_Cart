CREATE VIEW products_group AS
	SELECT price, SUM(price * qty) as 'subtotal' 
	FROM products 
	GROUP BY price 
	ORDER BY subtotal DESC;
