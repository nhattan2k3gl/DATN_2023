package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestLogin {
	public WebDriver driver;
	private XSSFWorkbook workbook;
	private XSSFSheet worksheet;
	private Map<String, Object[]> TestNGResult;
	private Map<String, String[]> dataLoginTest;

	private final String EXCEL_DIR = "D:\\DoanNhatTan_Ki7\\Java\\backend_java6\\DATN_2023\\rideExcel\\data\\";
	private final String IMAGE_DIR = "D:\\DoanNhatTan_Ki7\\Java\\backend_java6\\DATN_2023\\rideExcel\\images\\";

	// đọc dữ liệu từ file excel
	private void readDataFromExcel() {
		try {
			dataLoginTest = new HashMap<>(); // Khởi tạo HashMap để lưu trữ dữ liệu đăng nhập
			worksheet = workbook.getSheet("LoginData"); // Lấy sheet có tên "LoginData" từ workbook
			if (worksheet == null) {
				System.out.println("Không tìm thấy worksheet: LoginData");
			} else {
				Iterator<Row> rowIterator = worksheet.iterator(); // Lặp qua từng dòng trong sheet
				DataFormatter dataFormat = new DataFormatter(); // Đối tượng để định dạng dữ liệu từ ô cụ thể
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					if (row.getRowNum() >= 1) { // Bắt đầu từ dòng thứ hai vì dòng đầu tiên thường là tiêu đề
						Iterator<Cell> cellIterator = row.cellIterator();
						String key = ""; // Khóa (STT)
						String username = ""; // Giá trị ô username
						String password = ""; // Giá trị ô password
						String expected = ""; // Giá trị ô expected

						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							// Xác định giá trị của từng ô trong dòng
							switch (cell.getColumnIndex()) {
							case 0:
								key = dataFormat.formatCellValue(cell);
								break;
							case 1:
								username = dataFormat.formatCellValue(cell);
								break;
							case 2:
								password = dataFormat.formatCellValue(cell);
								break;
							case 3:
								expected = dataFormat.formatCellValue(cell);
								break;
							default:
								// Xử lý nếu có thêm cột khác cần đọc
								break;
							}
						}

						// Lưu thông tin vào HashMap với key là STT và value là một mảng chứa thông tin
						// username, password, expected
						String[] myArr = { username, password, expected };
						dataLoginTest.put(key, myArr);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("readDataFromExcel() : " + e.getMessage());
		}
	}
	// ---------- Kết thúc đọc dữ liệu -------------------

	// ---------- Xử lý chụp ảnh -------------------------
	// -----Hàm chụp ảnh------
	public void takeScreenShot(WebDriver driver, String outputSrc) throws IOException {
		// Sử dụng thư viện AShot để chụp ảnh màn hình với chiến lược viewportPasting
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);

		// Ghi ảnh vào một tệp PNG tại đường dẫn được chỉ định
		File outputFile = new File(outputSrc);
		ImageIO.write(screenshot.getImage(), "PNG", outputFile);
	}

	public void writeImage(String imgSrc, Row row, Cell cell, XSSFSheet sheet) throws IOException {
		// Đọc hình ảnh từ đường dẫn và chuyển đổi thành mảng bytes
		try (InputStream is = new FileInputStream(imgSrc)) {
			byte[] bytes = is.readAllBytes();

			// Thêm ảnh vào workbook và nhận lại ID của ảnh trong workbook
			int idImg = sheet.getWorkbook().addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);

			// Bắt buộc phải khởi tạo để đưa hình ảnh vào trong Excel
			XSSFDrawing drawing = sheet.createDrawingPatriarch();

			// Định vị hình ảnh trong tệp Excel sử dụng client anchor
			XSSFClientAnchor anchor = new XSSFClientAnchor();
			anchor.setCol1(cell.getColumnIndex() + 1);
			anchor.setRow1(row.getRowNum());
			anchor.setCol2(cell.getColumnIndex() + 2);
			anchor.setRow2(row.getRowNum() + 1);

			// Tạo hình ảnh trong drawing và đặt vị trí của nó
			drawing.createPicture(anchor, idImg);
		}

	}

	// ---------- Kết thúc Xử lý chụp ảnh ----------------

	// ------------ Before Class ------------
	@SuppressWarnings("deprecation")
	@BeforeClass(alwaysRun = true)
	public void suiteTest() {
		try {
			TestNGResult = new LinkedHashMap<String, Object[]>();
			driver = new ChromeDriver();
			driver.manage().window().maximize();

			// Đọc dữ liệu từ tệp Excel
			workbook = new XSSFWorkbook(new FileInputStream(new File(EXCEL_DIR + "Data.xlsx")));
			worksheet = workbook.getSheet("LoginData");
			readDataFromExcel();

			// Tạo workbook và worksheet mới cho kết quả kiểm thử
			workbook = new XSSFWorkbook();
			worksheet = workbook.createSheet("ResultTestLogin");

			// Tạo kiểu cho các ô trong bảng tính
			CellStyle rowStyle = workbook.createCellStyle();
			rowStyle.setAlignment(HorizontalAlignment.CENTER);
			rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			rowStyle.setWrapText(true);

			// Thêm dòng tiêu đề vào bảng tính
			TestNGResult.put("1", new Object[] { "STT", "Username", "Password", "Action", "Expected", "Actual",
					"Status", "Date Check", "LINK", "Image" });

		} catch (Exception e) {
			System.out.println("suiteTest() : " + e.getMessage());
		}
	}

	@AfterClass
	public void suiteTearDown() {
		Set<String> keyset = TestNGResult.keySet();
		int rownum = 0;
		for (String key : keyset) {
			// Tạo một kiểu cell mới cho mỗi dòng
			CellStyle rowStyle = workbook.createCellStyle();

			// Tạo một dòng mới trong bảng tính
			Row row = worksheet.createRow(rownum++);

			// Lấy mảng các đối tượng liên quan đến khóa hiện tại
			Object[] objArr = TestNGResult.get(key);

			int cellnum = 0;
			// Duyệt qua các đối tượng trong mảng
			for (Object obj : objArr) {
				// Tạo một ô mới trong dòng
				int flag = cellnum++;
				Cell cell = row.createCell(flag);

				// Kiểm tra loại của đối tượng và đặt giá trị ô tương ứng
				if (obj instanceof Date) {
					cell.setCellValue((Date) obj);
				} else if (obj instanceof Boolean) {
					cell.setCellValue((Boolean) obj);
				} else if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Double) {
					cell.setCellValue((Double) obj);
				}

				// Kiểm tra nếu đối tượng chứa "failure" và ".png"
				if (obj.toString().contains("failure") && obj.toString().contains(".png")) {
					try {
						// Đặt chiều cao của dòng để hiển thị hình ảnh
						row.setHeightInPoints(80);

						// Ghi hình ảnh vào ô
						writeImage(obj.toString(), row, cell, worksheet);

						// Tạo một siêu liên kết đến hình ảnh
						CreationHelper creationHelper = worksheet.getWorkbook().getCreationHelper();
						XSSFHyperlink hyperlink = (XSSFHyperlink) creationHelper.createHyperlink(HyperlinkType.URL);
						cell.setCellValue("Hình Đầy Đủ");
						hyperlink.setAddress(obj.toString().replace("\\", "/"));
						cell.setHyperlink(hyperlink);
					} catch (Exception d) {
						System.out.println("Ghi Ảnh: " + d.getMessage());
					}
				}

				// Đặt kiểu chung cho dòng
				rowStyle.setAlignment(HorizontalAlignment.CENTER);
				rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
				rowStyle.setWrapText(true);
				worksheet.autoSizeColumn(cellnum);
				worksheet.setColumnWidth(9, 7000);
				row.setRowStyle(rowStyle);
			}

			// Ghi file Excel vào đường dẫn đã chỉ định
			try {
				FileOutputStream out = new FileOutputStream(new File(EXCEL_DIR + "ResultsTestNGLogin.xlsx"));
				workbook.write(out);
				out.close();
				System.out.println("Lưu Thành Công vào File Excel!!!");
			} catch (Exception e) {
				System.out.println("suiteTearDown() : " + e.getMessage());
			}
		}

		// Đóng WebDriver
//		driver.close();

	}

	@Test
	public void LoginTest() {
		try {
			// Lấy danh sách các key từ một Map (`dataLoginTest`)
			Set<String> keySet = dataLoginTest.keySet();
			int index = 1;

			// Duyệt qua từng key và thực hiện đăng nhập
			for (String key : keySet) {
				// Lấy giá trị tương ứng của key, đây có thể là một bộ dữ liệu đăng nhập
				String[] value = dataLoginTest.get(key);
				String username = value[0];
				String password = value[1];
				String expected = value[2];

				// Lấy thời gian hiện tại và định dạng nó thành một chuỗi
				LocalDateTime myDateObj = LocalDateTime.now();
				DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss | dd-MM-yyyy ");
				String formattedDate = myDateObj.format(myFormatObj);

				// Mở trình duyệt và điền thông tin đăng nhập vào trang web
				driver.get("http://localhost:6969/login");
				driver.findElement(By.xpath("//*[@id=\"exampleInputEmail\"]")).sendKeys(username);
				driver.findElement(By.xpath("//*[@id=\"exampleInputPassword\"]")).sendKeys(password);

				// Tạm dừng để trang web có thể xử lý thông tin đăng nhập
				Thread.sleep(2000);

				// Tương tác với nút đăng nhập bằng Selenium WebDriver Actions
				WebElement btnLogin = driver
						.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/div[2]/div/form/button"));
				Actions actions = new Actions(driver).click(btnLogin);
				actions.build().perform();

				// Lấy tiêu đề thực tế của trang web sau khi đăng nhập
				String actualTitle = driver.getTitle();

				// Hiển thị thông tin đăng nhập trong console
				System.out.println(
						"--- " + username + " | " + password + " | " + expected + " | " + actualTitle + " ---");

				// Dừng chương trình trong 5 giây để quan sát kết quả (có thể loại bỏ trong môi
				// trường thực tế)
				Thread.sleep(5000);

				// Kiểm tra kết quả và ghi vào Map (`TestNGResult`)
				if (actualTitle.equalsIgnoreCase(expected)) {
					// Ghi lại kết quả "PASS" nếu đăng nhập thành công
					String path = IMAGE_DIR + "failure-" + System.currentTimeMillis() + ".png";
					takeScreenShot(driver, path);
					TestNGResult.put(String.valueOf(index + 1),
							new Object[] { String.valueOf(index), username, password, "Test Login", expected,
									actualTitle, "PASS", formattedDate, path.replace("\\", "/") });
				} else {
					// Xử lý khi đăng nhập không thành công, chụp ảnh và ghi lại thông tin lỗi
//					driver.findElement(By.xpath("//*[@id=\"exampleInputEmail\"]")).sendKeys(username);
//					driver.findElement(By.xpath("//*[@id=\"exampleInputPassword\"]")).sendKeys(password);
					String path = IMAGE_DIR + "failure-" + System.currentTimeMillis() + ".png";
					// Chụp ảnh màn hình và lưu lại đường dẫn
					takeScreenShot(driver, path);
					TestNGResult.put(String.valueOf(index + 1),
							new Object[] { String.valueOf(index), username, password, "Test Login", expected,
									actualTitle, "FALIED", formattedDate, path.replace("\\", "/") });
				}
				index++;
			}

			// Xử lý ngoại lệ nếu có
		} catch (Exception e) {
			System.out.println("LoginTest() : " + e.getMessage());
		} finally {
			// Đảm bảo rằng WebDriver sẽ được đóng ngay cả khi có ngoại lệ xảy ra
			driver.close();
		}
	}
}
