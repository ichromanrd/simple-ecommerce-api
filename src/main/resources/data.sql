INSERT INTO users (`id`, `username`, `first_name`, `last_name`, `password`, `email`, `phone`) VALUES
(1, 'admin', 'Administrator', NULL, '$2a$10$ao7XGhfaAdvzWOlfwq4vDuudTOyNinw9fR4BbBmIlw856YMTgzTmy', 'admin@blockchainspace.com', '000'), -- admin
(2, 'seller1', 'Seller 1', NULL, '$2a$10$WoYCFacIPGoVYSkbwqn6buEMzD046JeZV29l2UslfrINuBSO8goBq', 'seller1@blockchainspace.com', '000'), -- seller1
(3, 'seller2', 'Seller 2', NULL, '$2a$10$3OFIJ5.a2x2e9FEEa38C3Oprehq6Rj22c1ImVmjVKQ0hNQoV1Qzfm', 'seller2@blockchainspace.com', '000'), -- seller2
(4, 'buyer1', 'Buyer 1', NULL, '$2a$10$7LIgQykVhL38j/GNJY7bDOZuKSQhg6.xn4LZdyRj.7ZV9B/Nz8BN6', 'buyer1@blockchainspace.com', '000'), -- buyer1
(5, 'buyer2', 'Buyer 2', NULL, '$2a$10$qrTpBVicIQCo/T71uykFie.h.nBBk1EoJmZ9utZ4wdfBQXiEqqIp6', 'buyer2@blockchainspace.com', '000'); -- buyer2

INSERT INTO user_roles (`id`, `user_id`, `role_name`) VALUES
(1, 1, 'admin'),
(2, 2, 'seller'), (3, 3, 'seller'),
(4, 4, 'buyer'), (5, 5, 'buyer');

INSERT INTO roles_permissions (`id`, `role_name`, `permission`) VALUES
(1, 'admin', '*'),
(2, 'seller', 'product:seller-view'), (3, 'seller', 'product:manage'),
(4, 'buyer', 'product:view');