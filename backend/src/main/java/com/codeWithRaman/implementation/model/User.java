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
@Table(name = "account")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_ID")
    private Long userID;

	@Column(name = "username", unique = true, nullable = false)
    private String username;

	@Column(unique = true) 
	private String email;

	@Column(name = "password", nullable = false)
    private String password;

	@Column(name = "quota", nullable = false)
    private Integer quota;

	@Column(name = "role", nullable = false)
	private String role;

	@Column(name = "total_balance", nullable = false)
    private Integer totalBalance;

    @Column(name = "total_expenses", nullable = false)
    private Integer totalExpenses;

    @Column(name = "player_ID") 
    private Integer playerID;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
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