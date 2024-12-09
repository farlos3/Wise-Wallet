package com.codeWithRaman.implementation.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "account") // ตั้งชื่อ table (ถ้าจำเป็น)
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ใช้ AUTO_INCREMENT
    @Column(name = "user_ID") // แมปกับคอลัมน์ user_ID
    private Long userID;

	@Column(name = "username", unique = true, nullable = false) // แมปกับคอลัมน์ username
    private String username;

	@Column(unique = true) // ระบุให้ email เป็นค่า unique
	private String email;

	@Column(name = "password", nullable = false) // แมปกับคอลัมน์ password
    private String password;

	@Column(name = "quota", nullable = false) // แมปกับคอลัมน์ quota
    private Integer quota;

	@Column(name = "role", nullable = false)
	private String role;

	@Column(name = "total_balance", nullable = false) // แมปกับคอลัมน์ total_balance
    private Integer totalBalance;

    @Column(name = "total_expenses", nullable = false) // แมปกับคอลัมน์ total_expenses
    private Integer totalExpenses;

    @Column(name = "player_ID") // แมปกับคอลัมน์ player_ID (nullable)
    private Integer playerID;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false) // หากต้องการเก็บเวลา creation
    private LocalDateTime createdAt;

	// Getters และ Setters
	public Long getId() {
		return userID;
	}

	public void setId(Long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Integer totalBalance) {
        this.totalBalance = totalBalance;
    }

    public Integer getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Integer totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public Integer getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Integer playerID) {
        this.playerID = playerID;
    }

	public LocalDateTime getCreated_at() {
		return createdAt;
	}

	public void setCreated_at(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}