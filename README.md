# TKXDPM.20201-18

# Danh sách thành viên nhóm
- Đỗ Viết Trí - 20173412
- Trần Văn Trí - 20173410
- Nguyễn Ngọc Trinh - 20173413
- Nguyễn Mạnh Trường - 20177022

# Lab00
## Nhiệm vụ
- Hoàn thành đặc tả các use case : Xem thông tin bãi xe, xem thông tin xe, thuê xe, trả xe
- Hoàn thành tài liệu SRS
## Phân công nhiệm vụ
- Đỗ Viết Trí: Đặc tả use case xem thông tin bãi xe, quy trình nghiệp vụ của hệ thống, các yêu cầu khác
- Nguyễn Ngọc Trinh: Vẽ biểu đồ use case tổng quan, biểu đồ use case phân rã, đặc tả use case trả xe
- Trần Văn Trí: đặc tả use case thuê xe, từ điển thuật ngữ
- Nguyễn Mạnh Trường: đặc tả use case xem thông tin chi tiết của xe, phạm vi hệ thống
## Phân công nhiệm vụ review
- Đỗ Viết Trí : review công việc của Nguyễn Ngọc Trinh
    + Use case tổng quan: 
        + xem thông tin xe có thể được include từ xem thông tin bãi xe (do đã tách xem thông tin xe đang thuê ra 1 use case khác)
    + Phân rã use case: đầy đủ theo yêu cầu 
    + Đặc tả use case: đầy đủ theo yêu cầu
- Nguyễn Ngọc Trinh: review công việc của Trần Văn Trí
    + Đặc tả use case thuê xe
        + Tiền điều kiện phải là khách hàng đã có tài khoản trên hệ thống
        + Mô tả đúng các luồng sự kiện chính, luồng sự kiện thay thế, dữ liệu đầu vào, dữ liệu đầu ra
    + Từ điển thuật ngữ: Đầy đủ và chính xác các thuật ngữ trong tài liệu
- Trần Văn Trí: review công việc của Nguyễn Mạnh Trường
    + Use case xem thông tin xe
        + Mô tả đúng các luồng sự kiện, dữ liệu vào/ra của hệ thống
        + Cần mô tả mục Giới thiệu, luồng sự kiện chính chi tiết hơn
    + Phạm vi hệ thống
        + Trình bày đầy đủ và chi tiết
- Nguyễn Mạnh Trường: review công việc của Đỗ Viết Trí
    + Góp ý bổ sung:
    + Quy trình nghiệp vụ:
	    + Bỏ tiêu đề 2.4.1 vì không có 2.4.2
    + Đặc tả Use-case "Xem thông tin bãi xe"
	    + Phần Giới thiệu cần nêu rõ: cho phép khách hàng xem thông tin về các bãi xe, số xe trong bãi

# Lab 01
## Nhiệm vụ
- Thiết kế biểu đồ lớp tương tác (biểu đồ trình tự) và biểu đồ lớp phân tích cho từng use case mà mỗi thành viên phụ trách
- Thiết kế biểu đồ lớp phân tích chung cho toàn hệ thống
## Phân công nhiệm vụ
- Đỗ Viết Trí : Vẽ biểu đồ trình tự và biểu đồ lớp phân tích cho use case Xem thông tin bãi xe (View Bike in Station)
- Nguyễn Ngọc Trinh: Vẽ biểu đồ trình tự vào biểu đồ lớp phân tích cho use case trả xe (Return Bike)
- Nguyễn Mạnh Trường: Vẽ biểu đồ trình tự và biểu đồ lớp phân tích cho use case xem thông tin chi tiết xe (View Bike InFo)
- Trần Văn Trí: Vẽ biểu đồ trình tự và biểu đồ lớp phân tích cho use case thuê xe (Rent Bike)

## Phân công nhiệm vụ review
- Đỗ Viết Trí : review công việc của Nguyễn Ngọc Trinh (use case Return Bike)
    + Đối với biểu đồ lớp phân tích
        + Các giá trị trả về của các phương thức chưa đúng, cần xem xét lại
        + Tương đối đầy đủ các lớp
    + Đối với biểu đồ trình tự
        + Cũng tương tự như biểu đồ lớp phân tích, xem xét lại các giá trị trả về của phương thức
- Nguyễn Ngọc Trinh: review công việc của Trần Văn Trí (use case RentBike)
    + Đối với biểu đồ lớp phân tích: Vẽ đúng theo biểu đồ trình tự, đầy đủ, rõ ràng
    + Đối với biểu đồ trình tự: đầy đủ, rõ ràng
- Trần Văn Trí: review công việc của Nguyễn Mạnh Trường (use case View Bike Info)
    + Đối với biểu đồ lớp phân tích
        + Tương đối đầy đủ các lớp cần thiết
        + Nên đặt tên lớp ViewController thành tên khác dễ hiểu hơn
        + Chưa thấy được ý nghĩa của lớp RentBikeScreen
    + Đối với biểu đồ trình tự
        + Thể hiện đầy đủ các chức năng, rõ ràng
- Nguyễn Mạnh Trường: review công việc của Đỗ Viết Trí (use case View Bike in Station)
    + Đối với biểu đồ lớp phân tích
        + Phân tích đầy đủ các lớp cần thiết.
        + Có sự thống nhất giữa biểu đồ trình tự và lớp phân tích.
    + Đối với biểu đồ trình tự
        + Thể hiện đầy đủ các chức năng, rõ ràng
 
# Lab02
## Nhiệm vụ
- Thiết kế giao diện: Thiết kế giao diện người dùng, vẽ biểu đồ dịch chuyển màn hình và đặc tả chi tiết màn hình
- Thiết kế giao diện với hệ thống khác
- Các file đặt trong thư mục (DetailDesign/InterfaceDesign)
## Phân công nhiệm vụ
- Đỗ Viết Trí: Đặc tả chi tiết màn hình, Biểu đồ dịch chuyển màn hình
- Nguyễn Ngọc Trinh: Vẽ màn hình trang enter-qr-bike, các màn hình thông báo (notify)
- Nguyễn Mạnh Trường: Vẽ màn hình trang bike-detail, invoice 
- Trần Văn Trí: Vẽ màn hình trang station-detail, home, card-info 
## Phân công nhiệm vụ review
- Đỗ Viết Trí : review công việc của Nguyễn Ngọc Trinh
    + Hoàn thành đầy đủ các màn hình được giao
    + Nên chỉ thiết kế 1 màn hình thông báo, sau đó chỉ cần thanh nội dung thông báo
- Nguyễn Ngọc Trinh: review công việc của Trần Văn Trí
    + Hoàn thành đầy đủ các màn hình được giao
- Trần Văn Trí: review công việc của Nguyễn Mạnh Trường
    + Hoàn thành đầy đủ các màn hình được giao
- Nguyễn Mạnh Trường: review công việc của Đỗ Viết Trí
    + Đối với biểu đồ dịch chuyển 
        + Chưa thể hiện được các phụ thuộc giữa các màn hình
        + Đang còn đơn điệu màn hình này chuyển màn hình kia
    + Đối với đặc tả màn hình
        + Đặc tả đầy đủ các màn hình
# Lab03
## Nhiệm vụ
- Thiết kế lớp chi tiết cho từng use case mà thành viên phụ trách
- Thiết kế lớp chi tiết chung cho toàn hệ thống
- Các file đặt trong thư mục (DetailDesign/ClassDesign)

## Phân công nhiệm vụ
- Đỗ Viết Trí: Thiết kế lớp chi tiết cho use case: Xem thông tin bãi xe (View Bike in Station)
- Nguyễn Ngọc Trinh: Thiết kế lớp chi tiết cho use case Trả xe (ReturnBike)
- Nguyễn Mạnh Trường: Thiết kế lớp chi tiết cho use case Xem thông tin xe
- Trần Văn Trí: Thiết kế lớp chi tiết cho use case Thuê xe

## Phân công nhiệm vụ review
- Đỗ Viết Trí : review công việc của Nguyễn Ngọc Trinh (use case ReturnBike)
    + Tương đối đầy đủ các lớp
    + Chưa thể hiện được chức năng của màn hình PaymentScreen
    + Lớp thực thể Station thì không thể có phương thức displayInFoStation, hoặc có thể đổi tên thành getStationInFo
    + Mối quan hệ giữa các lớp chưa đúng 
    + Chưa có phương thức để lấy thông tin xe ở lớp thực thể Bike 
    + Ở lớp biên HomeScreen thì nên thêm phương thức để hiển thị, ví dụ như hiển thông tin của tất cả bãi đổi xe (displayAllStation) 
- Nguyễn Ngọc Trinh: review công việc của Trần Văn Trí (use case RentBike)
    + 
- Trần Văn Trí: review công việc của Nguyễn Mạnh Trường (use case View Bike InFo)
    + Tương đối đầy đủ các lớp
    + Chưa thể hiện được mối quan hệ giữa các lớp
    + Nên tách biệt lớp ViewController ra thành View gì, tên hơi khó hiểu
    
- Nguyễn Mạnh Trường: review công việc của Đỗ Viết Trí
    + 

# Lab04

## Nhiệm vụ
- Vẽ biểu đồ thực thể liên kết
- Thiết kế cơ sở dữ liệu
- Thiết kế chi tiết các thành phần trong cơ sở dữ liệu bám theo use case của từng thành viên
- Hoàn thiện tài liệu đặc tả (SDD)
- Các file đặt trong thư mục (DetailDesign/DataModeling)

## Phân công nhiệm vụ
- Đỗ Viết Trí: Vẽ biểu đồ thực thể liên kết
- Nguyễn Ngọc Trinh: Thiết kế cơ sở dữ liệu cho use case Trả xe (ReturnBike)
- Nguyễn Mạnh Trường: Thiết kế cơ sở dữ liệu cho use case Xem thông tin xe (ViewBikeInfo)
- Trần Văn Trí: Thiết kế cơ sở dữ liệu cho use case Thuê xe (RentBike)
