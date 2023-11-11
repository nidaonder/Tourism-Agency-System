

--
-- Veritabanı: `tourismAgency`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hostel_type`
--

CREATE TABLE `hostel_type` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`type` enum('Only Bed','Bed and Breakfast','Half Pension','Full Board','All Inclusive','Ultra All Inclusive','Full Credit Except For Alcohol') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hostel_type`
--

INSERT INTO `hostel_type` (`id`, `hotel_id`, `type`) VALUES
(1, 18, 'Bed and Breakfast'),
(2, 18, 'Half Pension'),
(3, 18, 'Full Board'),
(4, 16, 'All Inclusive'),
(5, 16, 'Ultra All Inclusive'),
(6, 16, 'Full Credit Except For Alcohol'),
(7, 15, 'Half Pension'),
(8, 15, 'Full Board'),
(9, 14, 'Only Bed'),
(10, 14, 'Bed and Breakfast'),
(11, 22, 'Only Bed'),
(12, 22, 'Bed and Breakfast'),
(13, 22, 'Half Pension'),
(14, 22, 'Full Board'),
(15, 22, 'All Inclusive'),
(16, 2, 'Full Board'),
(17, 2, 'All Inclusive'),
(18, 2, 'Ultra All Inclusive'),
(19, 13, 'Only Bed'),
(20, 13, 'Bed and Breakfast'),
(21, 13, 'Half Pension'),
(22, 23, 'Only Bed'),
(23, 23, 'Bed and Breakfast'),
(24, 24, 'Only Bed'),
(25, 24, 'Bed and Breakfast');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hotel`
--

CREATE TABLE `hotel` (
`id` int NOT NULL,
`name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`stars` enum('1','2','3','4','5') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`hotel_features` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `city`, `region`, `address`, `email`, `phone_number`, `stars`, `hotel_features`) VALUES
(2, 'Marriott Hotel', 'Istanbul', 'Marmara', 'Atasehir,Istanbul', 'info@marriott.com', '02165700000', '5', 'Free Parking\r Free Wi-Fi\r Fitness Center\r SPA'),
(13, 'Anemon Hotel', 'Samsun', 'Karadeniz', 'Canik,Samsun,Turkiye', 'anemon@otel.com', '05555051515', '5', 'SPA, Hotel Concierge, Free WiFi, Pool, Fitness Center'),
(14, 'Divan Hotel', 'Ankara', 'İc Anadolu', 'Merkez,Ankara,Turkiye', 'divan@hotel.com', '05676767676', '4', 'Free WiFi, SPA, CarPark'),
(15, 'Tempo Hotel', 'Istanbul', 'Marmara', 'Kagithane/istanbul/Turkiye', 'tempo@hotel.com', '05903033333', '3', 'free WiFi'),
(16, 'Sheraton Hotel', 'Izmir', 'Ege', 'Merkez/Izmir/Turkiye', 'sheraton@hotel.com', '05051555555', '4', 'SPA, Carpark, Pool, Fitness Center, Free WiFi'),
(18, 'Four Seasons Hotel', 'Istanbul', 'Marmara', 'Besiktas, Istanbul', 'fourseasons@hotel.com', '02124004040', '5', 'SPA, Free Wi-Fi, Pool'),
(22, 'Test Hotel', 'test', 'test', 'test', 'test@hotel.com', '05454900000', '3', 'Free WiFi'),
(23, 'test22', 'test22', 'testtt', 'test2312', 'test@gmail.com', '33333', '4', 'Tv'),
(24, 'aaa', 'aaa', 'aaa', 'aaa', 'aaa', 'aaa', '3', 'aaaa');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `reservation_info`
--

CREATE TABLE `reservation_info` (
`id` int NOT NULL,
`client_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`client_phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`client_email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`client_note` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`room_id` int NOT NULL,
`check_in` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`check_out` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`adult_num` int NOT NULL,
`child_num` int NOT NULL,
`total_price` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `reservation_info`
--

INSERT INTO `reservation_info` (`id`, `client_name`, `client_phone`, `client_email`, `client_note`, `room_id`, `check_in`, `check_out`, `adult_num`, `child_num`, `total_price`) VALUES
(8, 'test aaa tsttt', '3333ttt', 'testtest@hotmail.com', 'yok', 19, '30/03/2024', '01/04/2024', 1, 1, 9000),
(10, 'Ali Yildiz', '05444440000', 'ali@gmail.com', 'check-in 12:00', 13, '30/01/2024', '01/01/2024', 1, 0, 116000),
(11, 'Ayse Demir', '05333003030', 'ayse@gmail.com', 'check-in 14:00', 5, '02/02/2024', '03/02/2024', 2, 2, 6000),
(12, 'trd', 'vhgv', 'hjbkj', 'jhbjkb', 21, '04/09/2024', '05/09/2024', 1, 0, 1500),
(13, 'Nida Onder', '05455557676', 'nida@gmail.com', '-', 14, '10/02/2024', '12/02/2024', 1, 0, 2000),
(14, 'Batu', '05676667676', 'batu@gmail.com', '-', 5, '10/02/2024', '12/02/2024', 1, 0, 4000);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room`
--

CREATE TABLE `room` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`season_id` int NOT NULL,
`bed` int NOT NULL,
`room_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`hostel_type_id` int NOT NULL,
`remaining_rooms` int NOT NULL,
`adult_price` int NOT NULL,
`child_price` int NOT NULL,
`properties` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room`
--

INSERT INTO `room` (`id`, `hotel_id`, `season_id`, `bed`, `room_type`, `hostel_type_id`, `remaining_rooms`, `adult_price`, `child_price`, `properties`) VALUES
(2, 14, 7, 2, 'single', 9, 5, 1000, 500, 'TV'),
(3, 22, 8, 1, 'single', 11, 5, 500, 250, '-'),
(5, 14, 7, 4, 'double', 9, 9, 2000, 1000, 'TV'),
(6, 14, 7, 1, 'single', 9, 5, 500, 250, 'tv'),
(7, 14, 7, 1, 'suit', 10, 6, 3000, 1500, 'TV'),
(8, 2, 1, 1, 'single', 16, 5, 2500, 1250, 'TV, Minibar'),
(9, 2, 1, 2, 'double', 16, 8, 3000, 1500, 'TV,Minibar'),
(10, 2, 1, 4, 'suit', 16, 2, 4000, 2000, 'TV, Minibar'),
(11, 13, 3, 2, 'single', 19, 10, 2500, 1250, 'TV'),
(12, 13, 3, 2, 'single', 20, 10, 3000, 1500, 'TV'),
(13, 13, 3, 2, 'single', 21, 10, 4000, 2000, 'TV'),
(14, 15, 6, 3, 'double', 7, 4, 1000, 500, 'tv'),
(16, 15, 6, 1, 'single', 7, 6, 7, 6, 'tv'),
(18, 22, 8, 3, 'double', 11, 5, 1000, 500, 'TV'),
(19, 22, 8, 6, 'suit', 15, 2, 3000, 1500, 'TV, Minibar,Free WiFi'),
(20, 23, 9, 1, 'single', 22, 5, 1000, 500, 'Minibar'),
(21, 23, 9, 3, 'double', 12, 8, 1500, 750, 'Minibar'),
(22, 18, 2, 4, 'suit', 3, 8, 5000, 2500, 'Free WiFi, Carpark, Pool'),
(23, 18, 2, 2, 'single', 2, 5, 2500, 1500, 'Free WiFi, Carpark'),
(24, 16, 4, 4, 'suit', 5, 3, 6000, 4000, 'Free WiFi, Carpark, Pool, SPA'),
(25, 16, 4, 3, 'double', 4, 8, 5000, 3000, 'Free WiFi, Carpark, SPA, Pool'),
(26, 16, 4, 2, 'single', 4, 10, 3000, 1500, 'Free Wifi, Carpark, SPA, Pool'),
(27, 24, 10, 1, 'single', 24, 3, 5, 5, 'ff'),
(28, 13, 3, 2, 'single', 21, 0, 500, 250, 'TV'),
(29, 2, 1, 2, 'single', 16, 7, 1000, 500, 'Free WiFi');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `season`
--

CREATE TABLE `season` (
`id` int NOT NULL,
`hotel_id` int NOT NULL,
`season_start` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`season_end` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `season`
--

INSERT INTO `season` (`id`, `hotel_id`, `season_start`, `season_end`) VALUES
(1, 2, '01/12/2023', '01/10/2024'),
(2, 18, '01/02/2024', '01/11/2024'),
(3, 13, '01/01/2024', '01/07/2024'),
(4, 16, '01/04/2024', '01/10/2024'),
(6, 15, '01/01/2024', '01/06/2024'),
(7, 14, '01/12/2023', '01/04/2024'),
(8, 22, '23/03/2024', '23/08/2024'),
(9, 23, '01/08/2024', '01/12/2024'),
(10, 24, '01/01/2024', '01/01/2025');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

CREATE TABLE `user` (
`id` int NOT NULL,
`name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
`type` enum('admin','employee') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `name`, `uname`, `password`, `type`) VALUES
(1, 'nida onder', 'nidaonder', '1111', 'employee'),
(2, 'nida diril', 'nidadiril', '1111', 'admin'),
(11, 'batuhan', 'diril', '1111', 'admin'),
(14, 'TEST', 'TEST1', '2222', 'admin'),
(15, 'TEST12', 'TEST12', '3333', 'employee');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `hostel_type`
--
ALTER TABLE `hostel_type`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `hotel`
--
ALTER TABLE `hotel`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `reservation_info`
--
ALTER TABLE `reservation_info`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `room`
--
ALTER TABLE `room`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `season`
--
ALTER TABLE `season`
ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `user`
--
ALTER TABLE `user`
ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `hostel_type`
--
ALTER TABLE `hostel_type`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Tablo için AUTO_INCREMENT değeri `hotel`
--
ALTER TABLE `hotel`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Tablo için AUTO_INCREMENT değeri `reservation_info`
--
ALTER TABLE `reservation_info`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Tablo için AUTO_INCREMENT değeri `room`
--
ALTER TABLE `room`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Tablo için AUTO_INCREMENT değeri `season`
--
ALTER TABLE `season`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Tablo için AUTO_INCREMENT değeri `user`
--
ALTER TABLE `user`
MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
