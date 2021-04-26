package ru.navodnikov.denis.data.entity

data class MassageResult(
    val ok: Boolean,
    val result: Result
)

data class Result(
    val chat: Chat,
    val date: Int,
    val from: From,
    val message_id: Int,
    val text: String
)

data class From(
    val first_name: String,
    val id: Int,
    val is_bot: Boolean,
    val username: String
)

data class Chat(
    val all_members_are_administrators: Boolean,
    val id: Int,
    val title: String,
    val type: String
)