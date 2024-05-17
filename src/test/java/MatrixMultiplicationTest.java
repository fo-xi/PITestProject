import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixMultiplicationTest {
    private MatrixMultiplication matrixMultiplication;

    @BeforeEach
    public void setUp() {
        matrixMultiplication = new MatrixMultiplication();
    }

    @Nested
    class NumbersOperationsTests {
        @Test
        @Tag("NumbersOperations")
        @DisplayName("Check addition of numbers")
        public void testAddNumbers() {
            assertEquals(5, matrixMultiplication.addNumbers(2, 3),
                    "Expected 2 + 3 to equal 5");
            assertEquals(0, matrixMultiplication.addNumbers(-2, 2),
                    "Expected -2 + 2 to equal 0");
            assertEquals(-5, matrixMultiplication.addNumbers(-2, -3),
                    "Expected -2 + (-3) to equal -5");
        }

        @Test
        @Tag("NumbersOperations")
        @DisplayName("Check subtraction of numbers")
        public void testSubtractNumbers() {
            assertEquals(-1, matrixMultiplication.subtractNumbers(2, 3),
                    "Expected 2 - 3 to equal -1");
            assertEquals(0, matrixMultiplication.subtractNumbers(2, 2),
                    "Expected 2 - 2 to equal 0");
            assertEquals(1, matrixMultiplication.subtractNumbers(-2, -3),
                    "Expected -2 - (-3) to equal 1");
        }

        @Test
        @Tag("NumbersOperations")
        @DisplayName("Check multiplication of numbers")
        public void testMultiplyNumbers() {
            assertEquals(6, matrixMultiplication.multiplyNumbers(2, 3),
                    "Expected 2 * 3 to equal 6");
            assertEquals(-4, matrixMultiplication.multiplyNumbers(-2, 2),
                    "Expected -2 * 2 to equal -4");
            assertEquals(6, matrixMultiplication.multiplyNumbers(-2, -3),
                    "Expected -2 * (-3) to equal 6");
        }

        @Test
        @Tag("NumbersOperations")
        @DisplayName("Check division of numbers")
        public void testDivideNumbers() {
            assertEquals(2, matrixMultiplication.divideNumbers(6, 3),
                    "Expected 6 / 3 to equal 2");
            assertEquals(-1, matrixMultiplication.divideNumbers(-2, 2),
                    "Expected -2 / 2 to equal -1");
            assertEquals(1, matrixMultiplication.divideNumbers(-2, -2),
                    "Expected -2 / (-2) to equal 1");
        }

        @Test
        @Tag("NumbersOperations")
        @DisplayName("Check division by zero")
        public void testDivideNumbersByZero() {
            assertThrows(IllegalArgumentException.class, () -> matrixMultiplication.divideNumbers(6, 0),
                    "Expected divideNumbers(6, 0) to throw IllegalArgumentException");
        }

        @Test
        @Tag("NumbersOperations")
        @DisplayName("Check if number is not positive")
        public void testZeroIsNotPositive() {
            assertFalse(matrixMultiplication.isPositive(0),
                    "Expected 0 to not be positive");
        }

        @Test
        @Tag("NumbersOperations")
        @DisplayName("Check if number is not negative")
        public void testZeroIsNotNegative() {
            assertFalse(matrixMultiplication.isNegative(0),
                    "Expected 0 to not be negative");
        }

        @ParameterizedTest(name = "Check if a number is positive: {0}")
        @ValueSource(doubles = {134, 2, 45})
        @Tag("NumbersOperations")
        public void testIsPositive(double number) {
            assertTrue(matrixMultiplication.isPositive(number),
                    "Expected " + number + " to be positive");
        }

        @ParameterizedTest(name = "Check if a number is negative: {0}")
        @ValueSource(doubles = {-11, -785, -31})
        @Tag("NumbersOperations")
        public void testIsNegative(double number) {
            assertTrue(matrixMultiplication.isNegative(number),
                    "Expected " + number + " to be negative");
        }
    }

    @Nested
    class MatrixOperationsTests {
        @Test
        @Tag("MatrixOperations")
        @DisplayName("Check compatibility of matrices for multiplication")
        public void testCanMultiplyWithCompatibleMatrices() {
            double[][] matrixA = fillMatrix(2, 3);
            double[][] matrixB = fillMatrix(3, 2);
            assertTrue(matrixMultiplication.canMultiply(matrixA, matrixB),
                    "Expected canMultiply to return true for compatible matrices");
        }

        @Test
        @Tag("MatrixOperations")
        @DisplayName("Check incompatibility of matrices for multiplication")
        public void testCanMultiplyWithIncompatibleMatrices() {
            double[][] matrixA = fillMatrix(2, 3);
            double[][] matrixB = fillMatrix(2, 2);
            assertFalse(matrixMultiplication.canMultiply(matrixA, matrixB),
                    "Expected canMultiply to return false for incompatible matrices");
        }

        @Test
        @Tag("MatrixOperations")
        @DisplayName("Check compatibility of matrices for addition/subtraction")
        public void testCanAddOrSubtractWithSameSizeMatrices() {
            double[][] matrixA = fillMatrix(2, 3);
            double[][] matrixB = fillMatrix(2, 3);
            assertTrue(matrixMultiplication.canAddOrSubtract(matrixA, matrixB),
                    "Expected canAddOrSubtract to return true for matrices of the same size");
        }

        @Test
        @Tag("MatrixOperations")
        @DisplayName("Check incompatibility of matrices for addition/subtraction")
        public void testCanAddOrSubtractWithDifferentSizeMatrices() {
            double[][] matrixA = fillMatrix(2, 3);
            double[][] matrixB = fillMatrix(2, 2);
            assertFalse(matrixMultiplication.canAddOrSubtract(matrixA, matrixB),
                    "Expected canAddOrSubtract to return false for matrices of different sizes");
        }

        @Test
        @Tag("MatrixOperations")
        @DisplayName("Check multiplication of compatible matrices")
        public void testMultiplyWithCompatibleMatrices() {
            double[][] matrixA = {{1, 2, 3}, {4, 5, 6}};
            double[][] matrixB = {{7, 8}, {9, 10}, {11, 12}};
            double[][] expected = {{58, 64}, {139, 154}};
            assertArrayEquals(expected, matrixMultiplication.multiply(matrixA, matrixB),
                    "Expected multiplication of matrices to be correct");
        }

        @Test
        @Tag("MatrixOperations")
        @DisplayName("Check multiplication of incompatible matrices")
        public void testMultiplyWithIncompatibleMatrices() {
            double[][] matrixA = {{1, 2, 3}, {4, 5, 6}};
            double[][] matrixB = {{1, 2}, {3, 4}};
            assertThrows(IllegalArgumentException.class, () -> matrixMultiplication.multiply(matrixA, matrixB),
                    "Expected multiply to throw IllegalArgumentException for incompatible matrices");
        }

        @Test
        @Tag("MatrixOperations")
        @DisplayName("Check addition of same size matrices")
        public void testAddWithSameSizeMatrices() {
            double[][] matrixA = {{1, 2, 3}, {4, 5, 6}};
            double[][] matrixB = {{7, 8, 9}, {10, 11, 12}};
            double[][] expected = {{8, 10, 12}, {14, 16, 18}};
            assertArrayEquals(expected, matrixMultiplication.add(matrixA, matrixB),
                    "Expected addition of matrices to be correct");
        }

        @Test
        @Tag("MatrixOperations")
        @DisplayName("Check addition of different size matrices")
        public void testAddWithDifferentSizeMatrices() {
            double[][] matrixA = {{1, 2, 3}, {4, 5, 6}};
            double[][] matrixB = {{1, 2}, {3, 4}};
            assertThrows(IllegalArgumentException.class, () -> matrixMultiplication.add(matrixA, matrixB),
                    "Expected add to throw IllegalArgumentException for matrices of different sizes");
        }

        @Test
        @Tag("MatrixOperations")
        @DisplayName("Check subtraction of same size matrices")
        public void testSubtractWithSameSizeMatrices() {
            double[][] matrixA = {{1, 2, 3}, {4, 5, 6}};
            double[][] matrixB = {{7, 8, 9}, {10, 11, 12}};
            double[][] expected = {{-6, -6, -6}, {-6, -6, -6}};
            assertArrayEquals(expected, matrixMultiplication.subtract(matrixA, matrixB),
                    "Expected subtraction of matrices to be correct");
        }

        @Test
        @Tag("MatrixOperations")
        @DisplayName("Check subtraction of different size matrices")
        public void testSubtractWithDifferentSizeMatrices() {
            double[][] matrixA = {{1, 2, 3}, {4, 5, 6}};
            double[][] matrixB = {{1, 2}, {3, 4}};
            assertThrows(IllegalArgumentException.class, () -> matrixMultiplication.subtract(matrixA, matrixB),
                    "Expected subtract to throw IllegalArgumentException for matrices of different sizes");
        }

        private double[][] fillMatrix(int rows, int columns) {
            double[][] matrix = new double[rows][columns];
            Random random = new Random();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = random.nextDouble();
                }
            }

            return matrix;
        }
    }
}
