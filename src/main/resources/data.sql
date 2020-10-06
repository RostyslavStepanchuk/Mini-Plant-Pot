 -- variations
INSERT INTO variations(name) values ('size');
INSERT INTO variations(name) values ('color');
INSERT INTO variations(name) values ('form');

 -- variation_values
INSERT INTO variation_values (value, fk_variation_id) values ('S', 1);
INSERT INTO variation_values (value, fk_variation_id) values ('M', 1);
INSERT INTO variation_values (value, fk_variation_id) values ('L', 1);
INSERT INTO variation_values (value, fk_variation_id) values ('teal', 2);
INSERT INTO variation_values (value, fk_variation_id) values ('white', 2);
INSERT INTO variation_values (value, fk_variation_id) values ('round', 3);
INSERT INTO variation_values (value, fk_variation_id) values ('square', 3);
INSERT INTO variation_values (value, fk_variation_id) values ('cone', 3);


 -- pots
INSERT INTO pots (name, quantity) values ('superPuperPot', 6);
INSERT INTO pots (name, quantity) values ('colorfulBeautifulPot', 4);
INSERT INTO pots (name, quantity) values ('heartWarmingPut', 8);
INSERT INTO pots (name, quantity) values ('unresistablePot', 5);
INSERT INTO pots (name, quantity) values ('S Teal Round', 8);
INSERT INTO pots (name, quantity) values ('S Teal Square', 6);
INSERT INTO pots (name, quantity) values ('S Teal Cone', 6);
INSERT INTO pots (name, quantity) values ('M Teal Round', 7);
INSERT INTO pots (name, quantity) values ('M Teal Square', 5);
INSERT INTO pots (name, quantity) values ('M Teal Cone', 4);


 -- variation_attributes
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (1, 1, 2);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (2, 1, 3);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (3, 1, 2);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (4, 1, 2);
     -- S teal round
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (5, 1, 1);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (5, 2, 4);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (5, 3, 8);
     -- S teal square
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (6, 1, 1);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (6, 2, 4);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (6, 3, 7);
     -- S teal cone
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (7, 1, 1);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (7, 2, 4);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (7, 3, 6);
     -- M teal round
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (8, 1, 2);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (8, 2, 4);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (8, 3, 8);
     -- M teal square
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (9, 1, 2);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (9, 2, 4);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (9, 3, 7);
     -- M teal cone
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (10, 1, 2);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (10, 2, 4);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (10, 3, 6);

 -- sku's
INSERT INTO skus (id, is_set) values ('S_TEAL_ROUND', false);
INSERT INTO skus (id, is_set) values ('S_TEAL_SQUARE', false);
INSERT INTO skus (id, is_set) values ('S_TEAL_CONE', false);
INSERT INTO skus (id, is_set) values ('3M_TEAL_RO&SQ&CO', true);

 -- skus_have_pots
INSERT INTO skus_have_pots (fk_sku_id, fk_pot_id) values ('S_TEAL_ROUND', 5);
INSERT INTO skus_have_pots (fk_sku_id, fk_pot_id) values ('S_TEAL_SQUARE', 6);
INSERT INTO skus_have_pots (fk_sku_id, fk_pot_id) values ('S_TEAL_CONE', 7);
INSERT INTO skus_have_pots (fk_sku_id, fk_pot_id) values ('3M_TEAL_RO&SQ&CO', 8);
INSERT INTO skus_have_pots (fk_sku_id, fk_pot_id) values ('3M_TEAL_RO&SQ&CO', 9);
INSERT INTO skus_have_pots (fk_sku_id, fk_pot_id) values ('3M_TEAL_RO&SQ&CO', 10);

 -- production_orders
INSERT INTO production_orders (fk_pot_id, submit_date, initial_deadline, current_deadline, ordered_quantity)
  values (2, '20200814', '20200822', '20200822', 5);

INSERT INTO production_orders (fk_pot_id, submit_date, initial_deadline, current_deadline, deadline_changes, ordered_quantity)
  values (4, '20200814', '20200812', '20200818',1, 5);

 -- production_supplies
INSERT INTO production_supplies (fk_pot_id, supply_date, supplied_quantity, was_ordered)
  values (3, '20200810', 5, false);

 -- customer_orders
INSERT INTO customer_orders (etsy_order_id, submitted, deadline_to_send, is_closed, fk_sku_id)
  values ('2086140891', '20200812','20200820', false, 'S_TEAL_ROUND');

INSERT INTO customer_orders (etsy_order_id, submitted, deadline_to_send, is_closed, fk_sku_id)
  values ('2090024265', '20200812','20200820', false, '3M_TEAL_RO&SQ&CO');
