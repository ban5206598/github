package MyTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestOne {

	private String username;
	private String password;
	private int[] age;

	public void setUserName(String username) {
		this.username = username;
	}

	private void setPassWord(String password) {
		this.password = password;
	}

	public static void test01() throws ClassNotFoundException {

		Class c1 = TestOne.class;
		Class c2 = Class.forName("MyTest.TestOne");

		String package01 = c1.getPackage().getName();
		String package02 = c2.getPackage().getName();
		System.out.println("package01=" + package01);
		System.out.println("package02=" + package02);

		// ��ȡ���η�
		int mod = c1.getModifiers();
		String modifer = Modifier.toString(mod);
		System.out.println("modifier = " + modifer);

		// ��ȡָ�������ȫ�޶���
		String className = c1.getName();
		System.out.println("className =" + className);

		// ��ȡָ���ĸ���
		Class superClazz = c1.getSuperclass();
		String superClazzName = superClazz.getName();
		System.out.println("superClazzName =" + superClazzName);

		// ��ȡʵ�ֵĽӿ�
		Class[] interfaces = c1.getInterfaces();
		for (Class t : interfaces) {
			System.out.println("interfacesName =" + t.getName());
		}

		// ��ȡָ����ĳ�Ա����
		Field[] fields = c1.getDeclaredFields();
		for (Field field : fields) {

			String modifier = Modifier.toString(field.getModifiers());
			// �ֶεķ������η�
			Class type = field.getType();
			String name = field.getName();

			if (type.isArray()) {

				String arrType = type.getComponentType().getName() + "[]";
				System.out.println("" + modifier + " " + arrType + "" + name
						+ ";");

			} else {
				System.out.println("" + modifier + " " + type + " " + name
						+ ";");
			}

		}

		// ��ȡ��Ĺ��췽��
		Constructor[] constructors = c1.getDeclaredConstructors();
		for (Constructor constructor : constructors) {

			String name = constructor.getName();
			String modifier = Modifier.toString(constructor.getModifiers());
			System.out.println("" + modifier + " " + name + "(");

			Class[] paramTypes = constructor.getParameterTypes();// ��ȡ���췽���еĲ���
			for (int i = 0; i < paramTypes.length; i++) {
				if (i > 0) {
					System.out.print(",");
				}

				if (paramTypes[i].isArray()) {

					System.out.println(paramTypes[i].getComponentType()
							.getName() + "[]");

				} else {
					System.out.println(paramTypes[i].getName());
				}
			}
			System.out.println();
		}

		// ��ȡ��Ա����
		Method[] methods = c1.getDeclaredMethods();
		for (Method method : methods) {
			String modifier = Modifier.toString(method.getModifiers());
			Class returnType = method.getReturnType();
			if (returnType.isArray()) {
				String arrType = returnType.getComponentType().getName() + "[]";
				System.out.println("" + modifier + " " + arrType + " "
						+ method.getName() + "(");

			} else {

				System.out.print("" + modifier + " " + returnType.getName()
						+ " " + method.getName() + "(");
			}

			Class[] paramTypes = method.getParameterTypes();
			for (int i = 0; i < paramTypes.length; i++) {

				if (i > 0) {
					System.out.print(",");
				}
			}
		}

	}

	public static void test02() throws InstantiationException,
			IllegalAccessException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, InvocationTargetException {

		// ������÷���������ͨ��Method���invoke����ʵ�ֶ�̬�����ĵ���
		// public Object invoke(Object obj, Object..args)
		// ��һ�������������
		// �ڶ�����������ִ�з����ϵĲ���
		// ������Ҫ�������ĳ��˽�з��������������˽�з�����Ӧ��Method��������
		// ����setAccessible(true)
		Class c1 = TestOne.class;
		TestOne tt = (TestOne) c1.newInstance();
		System.out.println("username ==" + tt.username);
		System.out.println("password==" + tt.password);
		Method method = c1.getDeclaredMethod("setUserName", String.class);
		method.invoke(tt, "java����ѧϰ");

		System.out.println("username == " + tt.username);

		method = c1.getDeclaredMethod("setPassWord", String.class);
		method.setAccessible(true);
		method.invoke(tt, "����ִ��ĳ��Private���εķ���");
		System.out.println("passwrod == " + tt.password);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Java����Ĺ���
		// 1.�����ж�����ʱ������������
		// 2.�����ж�����ʱ���������еĳ�Ա�����ͷ���
		// 3.ͨ�������������Ե���private�ķ���
		// 4.���ɶ�̬����
		// ʵ��java�������
		// - Class�� ����ʾ�������е�JavaӦ�ó����е���ͽӿ�
		// - Field�� �ṩ�й����ӿڵ�������Ϣ���Լ������Ķ�̬����Ȩ��
		// - Constructor���ṩ������ĵ������췽������Ϣ�Լ������ķ���Ȩ��
		// - Method���ṩ�������ӿ���������������Ϣ

		// ע�⣺Class����Java����������Ҫ��һ�������࣬���л�ȡ�������Ϣ
		// (����������/����/���췽��/����Ȩ��)����Ҫ����ʵ��

		/*
		 * ��дJava�������Ĳ��� �������Ȼ�ȡһ�����Class���� ���� Class c1 = Test.class Class c2 =
		 * Class.forName("com.refection.Test"); Class c3 = new
		 * Test().getClass(); 2.Ȼ��ֱ����Class�����еķ�������ȡһ���������/����/���췽���Ľṹ
		 * ע�⣺���Ҫ�ܹ������Ļ�ȡ���ַ���/����/���췽��Ӧ���ص��������·����� Field Constructor Method
		 */

	}

}
