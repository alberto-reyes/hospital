package com.example.hospital.requestlogging;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

public class CachedServletInputStream extends ServletInputStream {

	private static Logger log = Logger.getLogger(CachedServletInputStream.class.getName());
	private InputStream cachedInputStream;

	public CachedServletInputStream(byte[] cachedBody) {
		this.cachedInputStream = new ByteArrayInputStream(cachedBody);
	}

	@Override
	public boolean isFinished() {
		try {
			return cachedInputStream.available() == 0;
		} catch (IOException exp) {
			log.log(Level.SEVERE, exp.getMessage());
		}
		return false;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setReadListener(ReadListener readListener) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int read() throws IOException {
		return cachedInputStream.read();
	}
}