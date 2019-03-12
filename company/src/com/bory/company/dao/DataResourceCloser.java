package com.bory.company.dao;

public class DataResourceCloser {
	public static void close(AutoCloseable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				// Exceptionó��
			}
		}
	}

	public static void close(AutoCloseable closeable1, AutoCloseable closeable2) {

		if (closeable1 != null) {
			try {
				closeable1.close();
			} catch (Exception e) {

			}
		}
		if (closeable2 != null) {
			try {
				closeable2.close();
			} catch (Exception e) {

			}
		}
	}
}