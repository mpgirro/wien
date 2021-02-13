package dev.stalla.model.itunes

/**
 * An iTunes-style subcategory that is contained within a parent [SimpleItunesCategory]:
 *
 * ```
 * <itunes:category text="News">
 *     <itunes:category text="Tech News" />
 * </itunes:category>
 * ```
 *
 * @param parent The parent [SimpleItunesCategory].
 */
public enum class NestedItunesCategory(public override val value: String, public val parent: SimpleItunesCategory) : ItunesCategory {
    BOOKS("Books", SimpleItunesCategory.ARTS),
    DESIGN("Design", SimpleItunesCategory.ARTS),
    FASHION_AND_BEAUTY("Fashion & Beauty", SimpleItunesCategory.ARTS),
    FOOD("Food", SimpleItunesCategory.ARTS),
    PERFORMING_ARTS("Performing Arts", SimpleItunesCategory.ARTS),
    VISUAL_ARTS("Visual Arts", SimpleItunesCategory.ARTS),
    CAREERS("Careers", SimpleItunesCategory.BUSINESS),
    ENTREPRENEURSHIP("Entrepreneurship", SimpleItunesCategory.BUSINESS),
    INVESTING("Investing", SimpleItunesCategory.BUSINESS),
    MANAGEMENT("Management", SimpleItunesCategory.BUSINESS),
    MARKETING("Marketing", SimpleItunesCategory.BUSINESS),
    NON_PROFIT("Non-Profit", SimpleItunesCategory.BUSINESS),
    COMEDY_INTERVIEWS("Comedy Interviews", SimpleItunesCategory.COMEDY),
    IMPROV("Improv", SimpleItunesCategory.COMEDY),
    STAND_UP("Stand-Up", SimpleItunesCategory.COMEDY),
    COURSES("Courses", SimpleItunesCategory.EDUCATION),
    HOW_TO("How To", SimpleItunesCategory.EDUCATION),
    LANGUAGE_LEARNING("Language Learning", SimpleItunesCategory.EDUCATION),
    SELF_IMPROVEMENT("Self-Improvement", SimpleItunesCategory.EDUCATION),
    COMEDY_FICTION("Comedy Fiction", SimpleItunesCategory.FICTION),
    DRAMA("Drama", SimpleItunesCategory.FICTION),
    SCIENCE_FICTION("Science Fiction", SimpleItunesCategory.FICTION),
    ALTERNATIVE_HEALTH("Alternative Health", SimpleItunesCategory.HEALTH_AND_FITNESS),
    FITNES("Fitness", SimpleItunesCategory.HEALTH_AND_FITNESS),
    MEDICINE("Medicine", SimpleItunesCategory.HEALTH_AND_FITNESS),
    MENTAL_HEALTH("Mental Health", SimpleItunesCategory.HEALTH_AND_FITNESS),
    NUTRITION("Nutrition", SimpleItunesCategory.HEALTH_AND_FITNESS),
    SEXUALITY("Sexuality", SimpleItunesCategory.HEALTH_AND_FITNESS),
    EDUCATION_FOR_KIDS("Education for Kids", SimpleItunesCategory.HEALTH_AND_FITNESS),
    PARENTING("Parenting", SimpleItunesCategory.HEALTH_AND_FITNESS),
    PETS_AND_ANIMALS("Pets & Animals", SimpleItunesCategory.HEALTH_AND_FITNESS),
    STORIES_FOR_KIDS("Stories for Kids", SimpleItunesCategory.HEALTH_AND_FITNESS),
    ANIMATION_AND_MANGA("Animation & Manga", SimpleItunesCategory.LEISURE),
    AUTOMOTIVE("Automotive", SimpleItunesCategory.LEISURE),
    AVIATION("Aviation", SimpleItunesCategory.LEISURE),
    CRAFTS("Crafts", SimpleItunesCategory.LEISURE),
    GAMES("Games", SimpleItunesCategory.LEISURE),
    HOBBIES("Hobbies", SimpleItunesCategory.LEISURE),
    HOME_AND_GARDEN("Home & Garden", SimpleItunesCategory.LEISURE),
    VIDEO_GAMES("Video Games", SimpleItunesCategory.LEISURE),
    MUSIC_COMMENTARY("Music Commentary", SimpleItunesCategory.MUSIC),
    MUSIC_HISTORY("Music History", SimpleItunesCategory.MUSIC),
    MUSIC_INTERVIEWS("Music Interviews", SimpleItunesCategory.MUSIC),
    BUSINESS_NEWS("Business News", SimpleItunesCategory.NEWS),
    DAILY_NEWS("Daily News", SimpleItunesCategory.NEWS),
    ENTERTAINMENT_NEWS("Entertainment News", SimpleItunesCategory.NEWS),
    NEWS_COMMENTARY("News Commentary", SimpleItunesCategory.NEWS),
    POLITICS("Politics", SimpleItunesCategory.NEWS),
    SPORTS_NEWS("Sports News", SimpleItunesCategory.NEWS),
    TECH_NEWS("Tech News", SimpleItunesCategory.NEWS),
    BUDDHISM("Buddhism", SimpleItunesCategory.RELIGION_AND_SPIRITUALITY),
    CHRISTIANITY("Christianity", SimpleItunesCategory.RELIGION_AND_SPIRITUALITY),
    HINDUISM("Hinduism", SimpleItunesCategory.RELIGION_AND_SPIRITUALITY),
    ISLAM("Islam", SimpleItunesCategory.RELIGION_AND_SPIRITUALITY),
    JUDAISM("Judaism", SimpleItunesCategory.RELIGION_AND_SPIRITUALITY),
    RELIGION("Religion", SimpleItunesCategory.RELIGION_AND_SPIRITUALITY),
    SPIRITUALITY("Spirituality", SimpleItunesCategory.RELIGION_AND_SPIRITUALITY),
    ASTRONOMY("Astronomy", SimpleItunesCategory.SCIENCE),
    CHEMISTRY("Chemistry", SimpleItunesCategory.SCIENCE),
    EARTH_SCIENCES("Earth Sciences", SimpleItunesCategory.SCIENCE),
    LIFE_SCIENCES("Life Sciences", SimpleItunesCategory.SCIENCE),
    MATHEMATICS("Mathematics", SimpleItunesCategory.SCIENCE),
    NEUTRAL_SCIENCES("Natural Sciences", SimpleItunesCategory.SCIENCE),
    NATURE("Nature", SimpleItunesCategory.SCIENCE),
    PHYSICS("Physics", SimpleItunesCategory.SCIENCE),
    SOCIAL_SCIENCES("Social Sciences", SimpleItunesCategory.SCIENCE),
    DOCUMENTARY("Documentary", SimpleItunesCategory.SOCIETY_AND_CULTURE),
    PERSONAL_JOURNALS("Personal Journals", SimpleItunesCategory.SOCIETY_AND_CULTURE),
    PHILOSOPHY("Philosophy", SimpleItunesCategory.SOCIETY_AND_CULTURE),
    PLACES_AND_TRAVEL("Places & Travel", SimpleItunesCategory.SOCIETY_AND_CULTURE),
    RELATIONSHIPS("Relationships", SimpleItunesCategory.SOCIETY_AND_CULTURE),
    BASEBALL("Baseball", SimpleItunesCategory.SPORTS),
    BASKETBALL("Basketball", SimpleItunesCategory.SPORTS),
    CRICKET("Cricket", SimpleItunesCategory.SPORTS),
    FANTASY_SPORTS("Fantasy Sports", SimpleItunesCategory.SPORTS),
    FOOTBALL("Football", SimpleItunesCategory.SPORTS),
    GOLF("Golf", SimpleItunesCategory.SPORTS),
    HOCKEY("Hockey", SimpleItunesCategory.SPORTS),
    RUGBY("Rugby", SimpleItunesCategory.SPORTS),
    RUNNING("Running", SimpleItunesCategory.SPORTS),
    SOCCER("Soccer", SimpleItunesCategory.SPORTS),
    SWIMMING("Swimming", SimpleItunesCategory.SPORTS),
    TENNIS("Tennis", SimpleItunesCategory.SPORTS),
    VOLLEYBALL("Volleyball", SimpleItunesCategory.SPORTS),
    WILDERNESS("Wilderness", SimpleItunesCategory.SPORTS),
    WRESTLING("Wrestling", SimpleItunesCategory.SPORTS),
    AFTER_SHOWS("After Shows", SimpleItunesCategory.TV_AND_FILM),
    FILM_HISTORY("Film History", SimpleItunesCategory.TV_AND_FILM),
    FILM_INTERVIEWS("Film Interviews", SimpleItunesCategory.TV_AND_FILM),
    FILM_REVIEWS("Film Reviews", SimpleItunesCategory.TV_AND_FILM),
    TV_REVIEWS("TV Reviews", SimpleItunesCategory.TV_AND_FILM)
}