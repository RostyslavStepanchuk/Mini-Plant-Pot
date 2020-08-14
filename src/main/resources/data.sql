/* variations */
INSERT INTO variations(name) values ('size');

/* variation_values */
INSERT INTO variation_values (value, fk_variation_id) values ('S', 1);
INSERT INTO variation_values (value, fk_variation_id) values ('M', 1);
INSERT INTO variation_values (value, fk_variation_id) values ('L', 1);

/* pots */
INSERT INTO pots (name, quantity) values ('superPuperPot', 6);
INSERT INTO pots (name, quantity) values ('colorfulBeautifulPot', 4);
INSERT INTO pots (name, quantity) values ('heartWarmingPut', 8);
INSERT INTO pots (name, quantity) values ('unresistablePot', 5);

/* variation_attributes */
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (1, 1, 2);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (2, 1, 3);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (3, 1, 2);
INSERT INTO variation_attributes (fk_pot_id, fk_variation_id, fk_variation_value_id) values (4, 1, 2);

/* sets */
INSERT INTO sets (name) values ('trippleMpots');

/* sets_have_pots */
INSERT INTO sets_have_pots (fk_set_id, fk_pot_id) values (1, 1);
INSERT INTO sets_have_pots (fk_set_id, fk_pot_id) values (1, 3);
INSERT INTO sets_have_pots (fk_set_id, fk_pot_id) values (1, 4);

/* production_orders */
INSERT INTO production_orders (fk_pot_id, submit_date, initial_deadline, current_deadline, ordered_quantity)
  values (2, '20200814', '20200822', '20200822', 5);

INSERT INTO production_orders (fk_pot_id, submit_date, initial_deadline, current_deadline, deadline_changes, ordered_quantity)
  values (4, '20200814', '20200812', '20200818',1, 5);
