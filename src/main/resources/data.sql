INSERT INTO user (`id`, `username`, `first_name`, `last_name`, `password`, `email`, `phone`) VALUES
(1, 'admin', 'Administrator', NULL, '$2a$12$WeiX1LChMMjScUuEAedweerYQv9mAEWNWJQvzZI2kRp8Hs8oJVys.', 'admin@blockchainspace.com', '000'), -- admin
(2, 'seller1', 'Seller 1', NULL, '$2a$12$6VJPQ3NdnXeEZoaZk9WNEe4nM7RSfD4dQfSLMb2mYhLWEoDqU/jpy', 'seller1@blockchainspace.com', '000'), -- seller1
(3, 'seller2', 'Seller 2', NULL, '$2a$12$9EIOiC51bEVr7hA3DzjkyuFeZLguyQXNWLdu9mCkg6DrJreL6boPS', 'seller2@blockchainspace.com', '000'), -- seller2
(4, 'buyer1', 'Buyer 1', NULL, '$2a$12$hSTGbRVrbZzr0mapoVKrtO2kuEzmGTmhh36SwJ5dcJX5e02QRmr/i', 'buyer1@blockchainspace.com', '000'), -- buyer1
(5, 'buyer2', 'Buyer 2', NULL, '$2a$12$QyMb8tMUwj8N3u.IwDcCGOt1h63fyGZxlQw0FqNEQ0FH/V7zDN3se', 'buyer2@blockchainspace.com', '000'); -- buyer2

INSERT INTO user_roles (`id`, `user_id`, `role_name`) VALUES
(1, 1, 'admin'),
(2, 2, 'seller'), (3, 3, 'seller'),
(4, 4, 'buyer'), (5, 5, 'buyer');

INSERT INTO roles_permissions (`id`, `role_name`, `permission`) VALUES
(1, 'admin', '*'),
(2, 'seller', 'product:seller-view'), (3, 'seller', 'product:manage'),
(4, 'buyer', 'product:view');