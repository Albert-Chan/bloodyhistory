package action.executor;

import gamelogic.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SheepListReader {

	private static final String sheepFile = "..\\..\\sheeps.txt";

	private LinkedList<Coordinate> sheeps = new LinkedList<Coordinate>();

	private int index = -1;
	private CopyOnWriteArrayList<Coordinate> rollback = new CopyOnWriteArrayList<Coordinate>();

	private ReadWriteLock lock = new ReentrantReadWriteLock();

	public SheepListReader() {
		parse();
	}

	public void reload() {
		lock.writeLock().lock();
		try {
			parse();
		} finally {
			lock.writeLock().unlock();
		}
	}

	public Coordinate get() {
		if (!rollback.isEmpty())
			return rollback.remove(0);
		lock.readLock().lock();
		try {
			index = (index + 1) % sheeps.size();
			return sheeps.get(index);
		} finally {
			lock.readLock().unlock();
		}
	}

	public void rollback(Coordinate c) {
		rollback.add(c);
	}

	private void parse() {
		sheeps.clear();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					getClass().getResourceAsStream(sheepFile)));
			String line;
			while (null != (line = reader.readLine())) {
				line = line.trim();
				if (line.indexOf("//") == 0) {
					continue;
				}
				sheeps.add(new Coordinate(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
