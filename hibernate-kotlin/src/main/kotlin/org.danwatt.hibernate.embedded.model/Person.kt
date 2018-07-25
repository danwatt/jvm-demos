package org.danwatt.hibernate.embedded.model

import javax.persistence.*

@Entity
@Table(name = "person")
data class Person(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        @Column(name = "name",nullable = false)
        var name: String
)