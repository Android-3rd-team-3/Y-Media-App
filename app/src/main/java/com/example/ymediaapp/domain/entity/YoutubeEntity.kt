package com.example.ymediaapp.domain.entity

import java.util.Date

data class YoutubeVideoEntity( // 동영상
    val thumbnail: String,// 썸네일 URL
    val name: String,// 이름
    val description: String, // 설명
    val videoId: String, // 해당 동영상의 ID
    val channelId: String, // 채널id
)

data class YoutubeVideoResultEntity(
    val kind: String,
    val eTag: String,
    val nextPageToken: String,
    val prevPageToken: String,
    val items: List<YoutubeVideoEntity>
)

data class YoutubeChannelEntity( // 채널
    val thumbnail: String,// 썸네일 URL
    val name: String,// 이름
    val description: String, // 설명
    val channelId: String
)

data class YoutubeChannelResultEntity(
    val kind: String,
    val eTag: String,
    val items: List<YoutubeChannelEntity>
)

data class CategoryEntity(
    val id: String,
    val name: String,
    val assignable: Boolean
)

data class CategoryResultEntity(
    val kind: String,
    val eTag: String,
    val items: List<CategoryEntity>
)

data class SearchVideoEntity(
    val thumbnail: String,
    val title: String,
    val channel: String,
    val id: String,
    val dateTime: Date
)

data class SearchVideoResultEntity(
    val kind: String,
    val eTag: String,
    val nextPageToken: String,
    val prevPageToken: String,
    val items: List<SearchVideoEntity>
)




/*
비디오     카테고리(한국)
id        title(kr)
1         영화/애니메이션
2         자동차
10        음악
15        반려동물/동물
17        스포츠
18        단편영화
19        여행/이벤트
20        게임
21        동영상 블로그
22        인물/블로그
23        코미디
24        엔터테인먼트
25        뉴스/정치
26        노하우/스타일
27        교육
28        과학기술
30        영화
31

 */