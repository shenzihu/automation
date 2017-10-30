package com.automation.test;

import org.neo4j.kernel.api.security.AccessMode.Static;

public class Test1 {
	public static void main(String[] args) {
		System.err.println(Test1.test());
	}
	public static int test() {
		int a = 0;
		try {
			return a;
		} finally {
			a++;
		}
	}
}
